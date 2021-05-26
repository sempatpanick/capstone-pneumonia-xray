package com.sempatpanick.pneumoniaxray.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.sempatpanick.pneumoniaxray.R
import com.sempatpanick.pneumoniaxray.core.data.Resource
import com.sempatpanick.pneumoniaxray.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
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
            homeViewModel.picture.observe(viewLifecycleOwner, { picture ->
                if (picture != null) {
                    when (picture) {
                        is Resource.Loading -> {
                            binding.progressBar.visibility = View.VISIBLE
                            binding.contentHome.visibility = View.GONE
                        }
                        is Resource.Success -> {
                            binding.progressBar.visibility = View.GONE
                            binding.contentHome.visibility = View.VISIBLE
                            val items = ArrayList<String>()
                            for (data in picture.data!!) {
                                items.add("${data.namaGambar} - ${data.lokasiGambar}")
                            }
                            val adapter = context?.let { ArrayAdapter(it, R.layout.items_select, items) }
                            (binding.selectImage.editText as? AutoCompleteTextView)?.setAdapter(adapter)
                        }
                        is Resource.Error -> {
                            binding.progressBar.visibility = View.GONE
                            binding.contentHome.visibility = View.VISIBLE
                            Toast.makeText(context, picture.message?: "Oops.. Something went wrong", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            })
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}