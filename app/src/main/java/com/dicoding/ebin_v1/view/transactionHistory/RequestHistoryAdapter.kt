package com.dicoding.ebin_v1.view.transactionHistory

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.ebin_v1.data.entity.Requests
import com.dicoding.ebin_v1.databinding.ItemRequestCompletionHistoryBinding

class RequestHistoryAdapter : ListAdapter<Requests, RequestHistoryAdapter.RequestHistoryViewHolder>(DIFF_CALLBACK) {

    class RequestHistoryViewHolder(private val binding: ItemRequestCompletionHistoryBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(requests: Requests) {
            binding.apply {
                txtRequestHistoryDateFulfill.text = requests.requestEnd
                txtRequestHistoryPoints.text = requests.reward.toString()
                txtRequestHistoryAmount.text = requests.amount.toString()
                txtRequestHistoryType.text = requests.type
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RequestHistoryViewHolder {
        val binding = ItemRequestCompletionHistoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RequestHistoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RequestHistoryViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        val DIFF_CALLBACK = object  : DiffUtil.ItemCallback<Requests>() {
            override fun areItemsTheSame(oldItem: Requests, newItem: Requests): Boolean = oldItem == newItem

            override fun areContentsTheSame(oldItem: Requests, newItem: Requests): Boolean = oldItem == newItem

        }
    }
}