package com.example.bikestations.ui.main.stationlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.bikestations.databinding.ListItemBinding
import com.example.bikestations.domain.BikeStation

class StationListAdapter(private val clickListener: BikeStationListener) :
    ListAdapter<BikeStation, StationListAdapter.BikeStationsViewHolder>(DiffCallback) {

    class BikeStationsViewHolder(
        private var binding: ListItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(bikeStation: BikeStation, clickListener: BikeStationListener) {
            binding.station = bikeStation
            binding.clickListener = clickListener
            // Force the data binding to execute immediately,
            // which allows the RecyclerView to make the correct view size measurements
            binding.executePendingBindings()
        }
    }

    // Allows the RecyclerView to determine which items have changed when the [List] of
    // [BikeStation] has been updated
    companion object DiffCallback : DiffUtil.ItemCallback<BikeStation>() {
        override fun areItemsTheSame(oldItem: BikeStation, newItem: BikeStation): Boolean {
            return oldItem.sno == newItem.sno
        }

        override fun areContentsTheSame(oldItem: BikeStation, newItem: BikeStation): Boolean {
            return oldItem.sna == newItem.sna
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BikeStationsViewHolder {
        return BikeStationsViewHolder(
            ListItemBinding.inflate(LayoutInflater.from(parent.context))
        )
    }

    override fun onBindViewHolder(holder: BikeStationsViewHolder, position: Int) {
        val bikeStation = getItem(position)
        holder.bind(bikeStation, clickListener)
    }
}

class BikeStationListener(val clickListener: (station: BikeStation) -> Unit) {
    fun onClick(station: BikeStation) = clickListener(station)
}