package com.example.bikestations.model

import android.app.Application
import androidx.lifecycle.*
import com.example.bikestations.domain.BikeStation
import com.example.bikestations.network.BikeApi
import kotlinx.coroutines.launch
import java.io.IOException

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val _stations = MutableLiveData<List<BikeStation>>()
    val stations: LiveData<List<BikeStation>> = _stations

    private val _station = MutableLiveData<BikeStation>()
    val station: LiveData<BikeStation> = _station

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
        _station.value = station
    }

    class Factory(private val app: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return MainViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}