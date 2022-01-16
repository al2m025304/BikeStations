package com.example.bikestations.network

data class BikeStation(
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
