package com.royal.tiger.androidweatherapp.data.repository

import com.royal.tiger.androidweatherapp.data.mapper.toWeatherInfo
import com.royal.tiger.androidweatherapp.data.remote.WeatherApi
import com.royal.tiger.androidweatherapp.domain.repository.WeatherRepository
import com.royal.tiger.androidweatherapp.domain.util.Resource
import com.royal.tiger.androidweatherapp.domain.weather.WeatherInfo
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val weatherApi: WeatherApi
) : WeatherRepository {

    override suspend fun getWeatherData(lat: Double, long: Double): Resource<WeatherInfo> {
        return try {
            Resource.Success(
                data = weatherApi.getWeatherData(
                    lat = lat,
                    long = long
                ).toWeatherInfo()
            )
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(
                message = e.message ?: "An unknown error occurred"
            )
        }
    }
}
