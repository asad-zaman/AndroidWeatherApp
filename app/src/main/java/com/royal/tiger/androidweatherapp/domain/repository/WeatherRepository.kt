package com.royal.tiger.androidweatherapp.domain.repository

import com.royal.tiger.androidweatherapp.domain.util.Resource
import com.royal.tiger.androidweatherapp.domain.weather.WeatherInfo

interface WeatherRepository {
    suspend fun getWeatherData(lat: Double, long: Double): Resource<WeatherInfo>
}