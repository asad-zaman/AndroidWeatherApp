package com.royal.tiger.androidweatherapp.data.weather

import com.google.gson.annotations.SerializedName

data class WeatherDataDto(
    @SerializedName("time")
    val times: List<String>,
    @SerializedName("temperature_2m")
    val temperatures: List<Double>,
    @SerializedName("relativehumidity_2m")
    val humidities: List<Double>,
    @SerializedName("windspeed_10m")
    val windSpeeds: List<Double>,
    @SerializedName("pressure_msl")
    val pressures: List<Double>,
    @SerializedName("weathercode")
    val weatherCodes: List<Int>
)
