package com.royal.tiger.androidweatherapp.data.remote

import com.royal.tiger.androidweatherapp.domain.weather.WeatherData
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    @GET("v1/forecast?latitude=52.52&longitude=13.41&hourly=temperature_2m,relativehumidity_2m,windspeed_10m,pressure_msl,weathercode")
    suspend fun getWeatherData(
        @Query("latitude") lat: Double,
        @Query("longitude") long: Double
    ): WeatherData
}