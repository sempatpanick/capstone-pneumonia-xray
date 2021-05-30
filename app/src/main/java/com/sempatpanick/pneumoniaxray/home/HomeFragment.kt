package com.sempatpanick.pneumoniaxray.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.jakewharton.rxbinding2.widget.RxTextView
import com.sempatpanick.pneumoniaxray.R
import com.sempatpanick.pneumoniaxray.core.data.Resource
import com.sempatpanick.pneumoniaxray.core.data.UserRepository
import com.sempatpanick.pneumoniaxray.core.manager.SessionManager
import com.sempatpanick.pneumoniaxray.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.Observable
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private lateinit var userRepository: UserRepository
    private val homeViewModel: HomeViewModel by viewModels()

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            val sesi = SessionManager(requireContext())
            userRepository = UserRepository.getInstance(sesi)

            setUp()

            homeViewModel.picture.observe(viewLifecycleOwner, { picture ->
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
                                picture.message ?: "Oops.. Something went wrong",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
            })
            binding.inputPatient.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {
                }

                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    lifecycleScope.launch {
                        homeViewModel.queryChannel.send(s.toString())
                    }
                }
            })
            homeViewModel.searchPatient.observe(viewLifecycleOwner, { result ->
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

    private fun showPatientMinimalAlert(isNotValid: Boolean) {
        binding.inputPatient.error = if (isNotValid) getString(R.string.patient_not_valid) else null
    }

    private fun showImageMinimalAlert(isNotValid: Boolean) {
        binding.inputImage.error = if (isNotValid) getString(R.string.image_not_valid) else null
    }
}