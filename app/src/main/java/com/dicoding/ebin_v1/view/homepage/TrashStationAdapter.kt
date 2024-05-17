package com.dicoding.ebin_v1.view.homepage

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.ebin_v1.data.entity.TrashStation
import com.dicoding.ebin_v1.data.response.TrashStationsResponseItem
import com.dicoding.ebin_v1.databinding.ItemTrashstationBinding
import com.dicoding.ebin_v1.view.trashStationDetail.TrashStationDetailActivity

class TrashStationAdapter : ListAdapter<TrashStationsResponseItem, TrashStationAdapter.MyViewHolder>(DIFF_CALLBACK){
    class MyViewHolder(private val binding: ItemTrashstationBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(trashStation: TrashStationsResponseItem) {
            binding.txtListTrashStationLogo.text = trashStation.id
            binding.txtListTrashStationId.text = trashStation.id
            binding.txtListTrashStationDistance.text = "200m"

            if (trashStation.available!!) {
                binding.txtListTrashStationAvailability.text = "Available"
            } else {
                binding.txtListTrashStationAvailability.text = "Not Available"
            }

            itemView.setOnClickListener {
                val intent = Intent(itemView.context, TrashStationDetailActivity::class.java)
                intent.putExtra(TrashStationDetailActivity.KEY_DETAIL, trashStation)
                itemView.context.startActivity(intent)

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemTrashstationBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val trashStation = getItem(position)
        holder.bind(trashStation)
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<TrashStationsResponseItem>() {
            override fun areItemsTheSame(oldItem: TrashStationsResponseItem, newItem: TrashStationsResponseItem): Boolean = oldItem == newItem

            override fun areContentsTheSame(oldItem: TrashStationsResponseItem, newItem: TrashStationsResponseItem): Boolean = oldItem == newItem

        }
    }
}