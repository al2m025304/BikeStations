package com.example.bikestations.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL =
    "https://tcgbusfs.blob.core.windows.net/dotapp/youbike/v2/"

// Build the Moshi object with Kotlin adapter factory that Retrofit will be using
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

// The Retrofit object with the Moshi converter
private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

// A public interface that exposes the [getStations] method
interface BikeApiService {
    @GET("youbike_immediate.json")
    suspend fun getStations(): List<BikeStation>
}

// A public Api object that exposes the lazy-initialized Retrofit service
object BikeApi {
    val retrofitService: BikeApiService by lazy { retrofit.create(BikeApiService::class.java) }
}