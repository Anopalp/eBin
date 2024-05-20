package com.dicoding.ebin_v1.view.marketplace

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.ebin_v1.data.entity.Requests
import com.dicoding.ebin_v1.data.response.GetAllRequestResponseItem
import com.dicoding.ebin_v1.databinding.ItemRequestsBinding
import com.dicoding.ebin_v1.view.requestDetail.RequestDetailActivity

class RequestAdapter : ListAdapter<GetAllRequestResponseItem, RequestAdapter.RequestViewHolder>(DIFF_CALLBACK) {
    class RequestViewHolder(private val binding: ItemRequestsBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(requests: GetAllRequestResponseItem) {
            binding.txtRequestOwner.text = requests.senderID
            binding.txtRequestAmount.text = requests.title
            binding.txtRequestType.text = ""
            binding.txtRequestRewardPointPlaceholder.text =requests.reward.toString()

            itemView.setOnClickListener {
                val intent = Intent(itemView.context, RequestDetailActivity::class.java)
                intent.putExtra(RequestDetailActivity.KEY_DETAIL, requests)
                itemView.context.startActivity(intent)
            }
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
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<GetAllRequestResponseItem>() {
            override fun areItemsTheSame(oldItem: GetAllRequestResponseItem, newItem: GetAllRequestResponseItem): Boolean = oldItem == newItem

            override fun areContentsTheSame(oldItem: GetAllRequestResponseItem, newItem: GetAllRequestResponseItem): Boolean = oldItem == newItem

        }
    }
}