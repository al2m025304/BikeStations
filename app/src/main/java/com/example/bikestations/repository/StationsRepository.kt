package com.example.bikestations.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.bikestations.database.StationsDatabase
import com.example.bikestations.database.asDomainModel
import com.example.bikestations.domain.BikeStation
import com.example.bikestations.network.BikeApi
import com.example.bikestations.network.asDatabaseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

private const val TAG = "StationsRepository"

class StationsRepository(private val database: StationsDatabase) {

    val stations: LiveData<List<BikeStation>> =
        Transformations.map(database.stationDao.getStations()) { it.asDomainModel() }

    suspend fun refreshStations() {
        withContext(Dispatchers.IO) {
            Log.d(TAG, "refresh stations is called")
            val stations = BikeApi.retrofitService.getStations()
            database.stationDao.insertAll(stations.asDatabaseModel())
        }
    }
}