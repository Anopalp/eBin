package com.dicoding.ebin_v1.view.transactionHistory

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.ebin_v1.data.entity.Requests
import com.dicoding.ebin_v1.data.response.RequestItem
import com.dicoding.ebin_v1.databinding.ItemRequestCompletionHistoryBinding
import java.util.Date
import java.util.concurrent.TimeUnit

class RequestHistoryAdapter : ListAdapter<RequestItem, RequestHistoryAdapter.RequestHistoryViewHolder>(DIFF_CALLBACK) {

    class RequestHistoryViewHolder(private val binding: ItemRequestCompletionHistoryBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(requests: RequestItem) {
            binding.apply {
                txtRequestHistoryDateFulfill.text = requests.end
                txtRequestHistoryPoints.text = requests.reward.toString()
                txtRequestHistoryAmount.text = requests.title
                txtRequestHistoryType.text = ""
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
        val DIFF_CALLBACK = object  : DiffUtil.ItemCallback<RequestItem>() {
            override fun areItemsTheSame(oldItem: RequestItem, newItem: RequestItem): Boolean = oldItem == newItem

            override fun areContentsTheSame(oldItem: RequestItem, newItem: RequestItem): Boolean = oldItem == newItem

        }
    }
}