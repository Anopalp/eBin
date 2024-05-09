package com.dicoding.ebin_v1.view.transactionHistory

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.ebin_v1.data.entity.Transaction
import com.dicoding.ebin_v1.databinding.ItemTrashStationHistoryBinding

class TrashStationHistoryAdapter : ListAdapter<Transaction, TrashStationHistoryAdapter.TrashStationHistoryViewHolder>(DIFF_CALLBACK) {

    class TrashStationHistoryViewHolder(private val binding: ItemTrashStationHistoryBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(transaction: Transaction) {
            binding.apply {
                txtTrashStationHistoryDateFulfill.text = transaction.date
                txtTrashStationHistoryPoints.text = transaction.points.toString()
                txtTrashStationHistoryAmount.text = transaction.amount.toString()
                txtTrashStationHistoryType.text = transaction.type
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
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Transaction>() {
            override fun areItemsTheSame(oldItem: Transaction, newItem: Transaction): Boolean = oldItem == newItem

            override fun areContentsTheSame(
                oldItem: Transaction,
                newItem: Transaction
            ): Boolean = oldItem == newItem

        }
    }
}