package com.example.bikestations.model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.bikestations.database.getDatabase
import com.example.bikestations.domain.BikeStation
import com.example.bikestations.repository.StationsRepository
import kotlinx.coroutines.launch
import java.io.IOException

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val stationsRepository = StationsRepository(getDatabase(application))
    val stations = stationsRepository.stations

    private val _station = MutableLiveData<BikeStation>()
    val station: LiveData<BikeStation>
        get() = _station

    private var _eventNetworkError = MutableLiveData(false)
    val eventNetworkError: LiveData<Boolean>
        get() = _eventNetworkError

    private var _isNetworkErrorShown = MutableLiveData(false)
    val isNetworkErrorShown: LiveData<Boolean>
        get() = _isNetworkErrorShown

    init {
        refreshDataFromRepository()
    }

    fun onBikeStationClicked(station: BikeStation) {
        _station.value = station
    }

    private fun refreshDataFromRepository() {
        viewModelScope.launch {
            try {
                stationsRepository.refreshStations()
                _eventNetworkError.value = false
                _isNetworkErrorShown.value = false
            } catch (networkError: IOException) {
                if (stations.value.isNullOrEmpty())
                    _eventNetworkError.value = true
            }
        }
    }

    fun onNetworkErrorShown() {
        _isNetworkErrorShown.value = true
    }
}