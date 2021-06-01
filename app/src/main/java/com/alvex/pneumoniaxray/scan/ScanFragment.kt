package com.alvex.pneumoniaxray.scan

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.alvex.pneumoniaxray.R
import com.alvex.pneumoniaxray.core.data.Resource
import com.alvex.pneumoniaxray.core.data.UserRepository
import com.alvex.pneumoniaxray.core.domain.model.ScanReq
import com.alvex.pneumoniaxray.core.manager.SessionManager
import com.alvex.pneumoniaxray.databinding.FragmentScanBinding
import com.jakewharton.rxbinding2.widget.RxTextView
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.Observable
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ScanFragment : Fragment(), View.OnClickListener {
    private lateinit var userRepository: UserRepository
    private val scanViewModel: ScanViewModel by viewModels()

    private var _binding: FragmentScanBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentScanBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            val sesi = SessionManager(requireContext())
            userRepository = UserRepository.getInstance(sesi)

            setUp()

            binding.btnScanNow.setOnClickListener(this)

            scanViewModel.picture.observe(viewLifecycleOwner, { picture ->
                if (picture != null) {
                    when (picture) {
                        is Resource.Loading -> {
                            binding.progressBar.visibility = View.VISIBLE
                        }
                        is Resource.Success -> {
                            binding.progressBar.visibility = View.GONE
                            val items = ArrayList<String>()
                            for (data in picture.data!!) {
                                items.add("${data.id} - ${data.nama} - ${data.url}")
                            }
                            val adapter =
                                context?.let { ArrayAdapter(it, R.layout.items_select, items) }
                            (binding.selectImage.editText as? AutoCompleteTextView)?.setAdapter(
                                adapter
                            )
                        }
                        is Resource.Error -> {
                            binding.progressBar.visibility = View.GONE
                            Toast.makeText(
                                context,
                                picture.message
                                    ?: resources.getString(R.string.something_wrong_message),
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
            })

            scanViewModel.searchPatient.observe(viewLifecycleOwner, { result ->
                val patientName = arrayListOf<String?>()
                result.map {
                    patientName.add("${it.id} - ${it.nama}")
                }
                val adapter = context?.let {
                    ArrayAdapter(
                        it,
                        android.R.layout.select_dialog_item,
                        patientName
                    )
                }
                adapter?.notifyDataSetChanged()
                binding.inputPatient.setAdapter(adapter)
            })

            scanViewModel.scanData.observe(viewLifecycleOwner, { result ->
                binding.progressBar.visibility = View.GONE
                Toast.makeText(context, result.message, Toast.LENGTH_LONG).show()
                if (result.status) {
                    binding.layoutResult.visibility = View.VISIBLE
                    binding.tvIdPatient.text = result.data?.idPatient
                    binding.tvPatientName.text = result.data?.name
                    binding.tvPrediction.text = result.data?.prediction
                }
            })
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            binding.btnScanNow.id -> scanNow()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    @SuppressLint("CheckResult")
    private fun setUp() {
        val imageStream = RxTextView.textChanges(binding.inputImage)
            .skipInitialValue()
            .map { image ->
                image.isEmpty()
            }
        imageStream.subscribe {
            showImageMinimalAlert(it)
        }

        val patientStream = RxTextView.textChanges(binding.inputPatient)
            .skipInitialValue()
            .map { patient ->
                lifecycleScope.launch {
                    scanViewModel.queryChannel.send(patient.toString())
                }
                patient.isEmpty()
            }
        patientStream.subscribe {
            showPatientMinimalAlert(it)
        }

        val invalidFieldsStream = Observable.combineLatest(
            imageStream,
            patientStream,
            { imageInvalid: Boolean, patientInvalid: Boolean ->
                !imageInvalid && !patientInvalid
            })
        invalidFieldsStream.subscribe { isValid ->
            binding.btnScanNow.isEnabled = isValid
        }
    }

    private fun scanNow() {
        binding.progressBar.visibility = View.VISIBLE
        val dataPost = ScanReq(
            binding.inputPatient.text.toString().take(5),
            userRepository.getUser().id.toString(),
            binding.inputImage.text.toString().take(5)
        )
        lifecycleScope.launch {
            scanViewModel.scanChannel.send(dataPost)
        }
    }

    private fun showPatientMinimalAlert(isNotValid: Boolean) {
        binding.inputPatient.error = if (isNotValid) getString(R.string.patient_not_valid) else null
    }

    private fun showImageMinimalAlert(isNotValid: Boolean) {
        binding.inputImage.error = if (isNotValid) getString(R.string.image_not_valid) else null
    }
}