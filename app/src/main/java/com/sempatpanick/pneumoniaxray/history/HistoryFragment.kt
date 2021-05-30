package com.sempatpanick.pneumoniaxray.history

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.sempatpanick.pneumoniaxray.R
import com.sempatpanick.pneumoniaxray.core.data.Resource
import com.sempatpanick.pneumoniaxray.core.ui.HistoryAdapter
import com.sempatpanick.pneumoniaxray.databinding.FragmentHistoryBinding
import com.sempatpanick.pneumoniaxray.detail.DetailHistoryActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HistoryFragment : Fragment() {

    private val historyViewModel: HistoryViewModel by viewModels()

    private var _binding: FragmentHistoryBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHistoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            val historyAdapter = HistoryAdapter()
            historyAdapter.onItemClick = { selectedData ->
                val intent = Intent(activity, DetailHistoryActivity::class.java)
//                intent.putExtra(DetailHistoryActivity.EXTRA_DATA, selectedData)
                startActivity(intent)
            }

            historyViewModel.history.observe(viewLifecycleOwner, { history ->
                if (history != null) {
                    when (history) {
                        is Resource.Loading -> binding.progressBar.visibility = View.VISIBLE
                        is Resource.Success -> {
                            binding.progressBar.visibility = View.GONE
                            historyAdapter.setData(history.data)
                        }
                        is Resource.Error -> {
                            binding.progressBar.visibility = View.GONE
                            Toast.makeText(
                                context,
                                history.message ?: "Oops.. Something went wrong",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
            })

            with(binding.rvHistory) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = historyAdapter
            }
        }
    }
}