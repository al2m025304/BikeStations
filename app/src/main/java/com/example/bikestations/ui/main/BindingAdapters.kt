package com.example.bikestations.ui.main

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.bikestations.network.BikeStation

// Updates the data shown in the [RecyclerView]
@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<BikeStation>?) {
    val adapter = recyclerView.adapter as StationListAdapter
    adapter.submitList(data)
}
