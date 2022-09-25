package com.royal.tiger.androidweatherapp.presentation

import com.royal.tiger.androidweatherapp.domain.weather.WeatherInfo

data class WeatherState(
    val error: String? = null,
    val loading: Boolean = false,
    val data: WeatherInfo? = null
)
