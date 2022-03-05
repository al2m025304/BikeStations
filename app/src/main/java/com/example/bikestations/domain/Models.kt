package com.example.bikestations.domain

data class BikeStation(
    val sno: String,
    val sna: String,
    val tot: Int,
    val sbi: Int,
    val sarea: String,
    val lat: Double,
    val lng: Double,
)
