package com.dicoding.ebin_v1.view.marketplace

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.ebin_v1.data.entity.Requests
import com.dicoding.ebin_v1.databinding.ItemRequestsBinding

class RequestAdapter : ListAdapter<Requests, RequestAdapter.RequestViewHolder>(DIFF_CALLBACK) {
    class RequestViewHolder(private val binding: ItemRequestsBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(requests: Requests) {
            binding.txtRequestOwner.text = requests.owner
            binding.txtRequestAmount.text = requests.amount.toString()
            binding.txtRequestType.text = requests.type
            binding.txtRequestRewardPointPlaceholder.text =requests.reward.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RequestViewHolder {
        val binding = ItemRequestsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RequestViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RequestViewHolder, position: Int) {
        val requests = getItem(position)
        holder.bind(requests)
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Requests>() {
            override fun areItemsTheSame(oldItem: Requests, newItem: Requests): Boolean = oldItem == newItem

            override fun areContentsTheSame(oldItem: Requests, newItem: Requests): Boolean = oldItem == newItem

        }
    }
}