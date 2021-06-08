package com.alvex.pneumoniaxray.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alvex.pneumoniaxray.R
import com.alvex.pneumoniaxray.core.domain.model.History
import com.alvex.pneumoniaxray.databinding.ItemsHistoryBinding
import java.util.*

class HistoryAdapter : RecyclerView.Adapter<HistoryAdapter.ListViewHolder>() {
    private var listData = ArrayList<History>()
    var onItemClick: ((History) -> Unit)? = null

    fun setData(newListData: List<History>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ListViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.items_history, parent, false)
        )

    override fun getItemCount() = listData.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemsHistoryBinding.bind(itemView)
        fun bind(data: History) {
            with(binding) {
                tvIdPatient.text = data.idPasien
                tvPatientName.text = data.namaPasien
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listData[bindingAdapterPosition])
            }
        }
    }
}