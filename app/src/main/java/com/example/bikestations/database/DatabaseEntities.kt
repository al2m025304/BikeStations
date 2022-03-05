package com.example.bikestations.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.bikestations.domain.BikeStation

@Entity(tableName = "database_station")
data class DatabaseStation(
    @PrimaryKey
    val sno: String,
    val sna: String,
    val tot: Int,
    val sbi: Int,
    val sarea: String,
    val mday: String,
    val lat: Double,
    val lng: Double,
    val ar: String,
    val sareaen: String,
    val snaen: String,
    val aren: String,
    val bemp: Int,
    val act: String,
    @ColumnInfo(name = "src_update_time") val srcUpdateTime: String,
    @ColumnInfo(name = "update_time") val updateTime: String,
    @ColumnInfo(name = "info_time") val infoTime: String,
    @ColumnInfo(name = "info_date") val infoDate: String,
)

fun List<DatabaseStation>.asDomainModel(): List<BikeStation> {
    return map {
        BikeStation(
            sno = it.sno,
            sna = it.sna,
            tot = it.tot,
            sbi = it.sbi,
            sarea = it.sarea,
            lat = it.lat,
            lng = it.lng,
        )
    }
}
