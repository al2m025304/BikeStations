package com.example.bikestations.model

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

    private val _name = MutableLiveData<String>()
    val name: LiveData<String> = _name

    private val _total = MutableLiveData<Int>()
    val total: LiveData<Int> = _total

    private val _bikes = MutableLiveData<Int>()
    val bikes: LiveData<Int> = _bikes

    private val _area = MutableLiveData<String>()
    val area: LiveData<String> = _area

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

    fun onBikeStationClicked(station: BikeStation) {
        _name.value = station.sna
        _total.value = station.tot
        _bikes.value = station.sbi
        _area.value = station.sarea
    }
}