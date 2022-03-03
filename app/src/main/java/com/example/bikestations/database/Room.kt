package com.example.bikestations.database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface StationDao {
    @Query("SELECT * FROM database_station")
    fun getStations(): LiveData<List<DatabaseStation>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(stations: List<DatabaseStation>)
}

@Database(entities = [DatabaseStation::class], version = 1)
abstract class StationsDatabase : RoomDatabase() {
    abstract val stationDao: StationDao
}

private lateinit var INSTANCE: StationsDatabase

fun getDatabase(context: Context): StationsDatabase {
    synchronized(StationsDatabase::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(
                context.applicationContext,
                StationsDatabase::class.java,
                "stations"
            ).build()
        }
    }
    return INSTANCE
}