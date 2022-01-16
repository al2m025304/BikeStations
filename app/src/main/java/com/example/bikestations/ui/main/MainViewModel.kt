package com.example.bikestations.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bikestations.network.BikeApi
import com.example.bikestations.network.BikeStation
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val _stations = MutableLiveData<List<BikeStation>>()
    val stations: LiveData<List<BikeStation>> = _stations

    init {
        getBikeStations()
    }

    private fun getBikeStations() {
        viewModelScope.launch {
            try {
                _stations.value = BikeApi.retrofitService.getStations()
            } catch (e: Exception) {
                _stations.value = listOf()
            }
        }
    }
}