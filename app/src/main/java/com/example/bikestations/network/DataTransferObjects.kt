package com.example.bikestations.network

import com.example.bikestations.database.DatabaseStation
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class YouBikeStation(
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
    val srcUpdateTime: String,
    val updateTime: String,
    val infoTime: String,
    val infoDate: String,
)

fun List<YouBikeStation>.asDatabaseModel(): List<DatabaseStation> {
    return map {
        DatabaseStation(
            sno = it.sno,
            sna = it.sna,
            tot = it.tot,
            sbi = it.sbi,
            sarea = it.sarea,
            mday = it.mday,
            lat = it.lat,
            lng = it.lng,
            ar = it.ar,
            sareaen = it.sareaen,
            snaen = it.snaen,
            aren = it.aren,
            bemp = it.bemp,
            act = it.act,
            srcUpdateTime = it.srcUpdateTime,
            updateTime = it.updateTime,
            infoTime = it.infoTime,
            infoDate = it.infoDate
        )
    }
}
