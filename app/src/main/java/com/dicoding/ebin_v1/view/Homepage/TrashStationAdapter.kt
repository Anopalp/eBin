package com.dicoding.ebin_v1.view.Homepage

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.ebin_v1.data.entity.TrashStation
import com.dicoding.ebin_v1.databinding.ItemTrashstationBinding

class TrashStationAdapter : ListAdapter<TrashStation, TrashStationAdapter.MyViewHolder>(DIFF_CALLBACK){
    class MyViewHolder(private val binding: ItemTrashstationBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(trashStation: TrashStation) {
            binding.txtListTrashStationLogo.text = trashStation.trashStationId
            binding.txtListTrashStationId.text = trashStation.trashStationId
            binding.txtListTrashStationDistance.text = trashStation.distance.toString()

            if (trashStation.capacity < 100) {
                binding.txtListTrashStationAvailability.text = "Available"
            } else {
                binding.txtListTrashStationAvailability.text = "Not Available"
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
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<TrashStation>() {
            override fun areItemsTheSame(oldItem: TrashStation, newItem: TrashStation): Boolean = oldItem == newItem

            override fun areContentsTheSame(oldItem: TrashStation, newItem: TrashStation): Boolean = oldItem == newItem

        }
    }
}