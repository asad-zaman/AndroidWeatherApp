package com.royal.tiger.androidweatherapp.domain.weather

import java.time.LocalDateTime

data class WeatherData(
    val time: LocalDateTime,
    val temperature: Double,
    val humidity: Double,
    val windSpeed: Double,
    val pressure: Double,
    val weatherType: WeatherType
)