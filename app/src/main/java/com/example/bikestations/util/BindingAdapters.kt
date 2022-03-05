package com.example.bikestations.util

import android.view.View
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.bikestations.domain.BikeStation
import com.example.bikestations.ui.main.stationlist.StationListAdapter

// Hide the spinner once data is available.
@BindingAdapter("isNetworkError", "stations")
fun hideIfNetworkError(view: View, isNetWorkError: Boolean, stations: Any?) {
    view.visibility = if (stations != null) View.GONE else View.VISIBLE

    if (isNetWorkError) {
        view.visibility = View.GONE
    }
}

// Updates the data shown in the [RecyclerView]
@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<BikeStation>?) {
    val adapter = recyclerView.adapter as StationListAdapter
    adapter.submitList(data)
}