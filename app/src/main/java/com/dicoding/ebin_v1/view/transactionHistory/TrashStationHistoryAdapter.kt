package com.dicoding.ebin_v1.view.transactionHistory

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.ebin_v1.data.entity.Transaction
import com.dicoding.ebin_v1.data.response.TransactionItem
import com.dicoding.ebin_v1.databinding.ItemTrashStationHistoryBinding
import java.util.Date
import java.util.concurrent.TimeUnit

class TrashStationHistoryAdapter : ListAdapter<TransactionItem, TrashStationHistoryAdapter.TrashStationHistoryViewHolder>(DIFF_CALLBACK) {

    class TrashStationHistoryViewHolder(private val binding: ItemTrashStationHistoryBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(transaction: TransactionItem) {
            binding.apply {
                txtTrashStationHistoryDateFulfill.text = transaction.timestamp
                txtTrashStationHistoryPoints.text = transaction.reward.toString()
                txtTrashStationHistoryAmount.text = transaction.trash?.paper.toString()
                txtTrashStationHistoryType.text = "paper"
                txtTrashStationHistoryLocation.text = transaction.trashStation
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TrashStationHistoryViewHolder {
        val binding = ItemTrashStationHistoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TrashStationHistoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TrashStationHistoryViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<TransactionItem>() {
            override fun areItemsTheSame(oldItem: TransactionItem, newItem: TransactionItem): Boolean = oldItem == newItem

            override fun areContentsTheSame(
                oldItem: TransactionItem,
                newItem: TransactionItem
            ): Boolean = oldItem == newItem

        }
    }
}