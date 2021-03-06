package com.alvex.pneumoniaxray.history

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.alvex.pneumoniaxray.R
import com.alvex.pneumoniaxray.core.data.Resource
import com.alvex.pneumoniaxray.core.ui.HistoryAdapter
import com.alvex.pneumoniaxray.databinding.FragmentHistoryBinding
import com.alvex.pneumoniaxray.detail.DetailHistoryActivity
import dagger.hilt.android.AndroidEntryPoint

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
                intent.putExtra(DetailHistoryActivity.EXTRA_DATA, selectedData)
                startActivity(intent)
            }

            historyViewModel.history.observe(viewLifecycleOwner, { history ->
                if (history != null) {
                    when (history) {
                        is Resource.Loading -> binding.progressBar.visibility = View.VISIBLE
                        is Resource.Success -> {
                            binding.progressBar.visibility = View.GONE
                            if (!history.data.isNullOrEmpty()) {
                                historyAdapter.setData(history.data)
                            } else {
                                Toast.makeText(context, getString(R.string.something_wrong_message), Toast.LENGTH_SHORT).show()
                            }
                        }
                        is Resource.Error -> {
                            binding.progressBar.visibility = View.GONE
                            Toast.makeText(
                                context,
                                history.message ?: resources.getString(R.string.something_wrong_message),
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