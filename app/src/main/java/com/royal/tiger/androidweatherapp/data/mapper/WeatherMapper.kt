package com.royal.tiger.androidweatherapp.data.mapper

import com.royal.tiger.androidweatherapp.data.weather.WeatherDataDto
import com.royal.tiger.androidweatherapp.data.weather.WeatherDto
import com.royal.tiger.androidweatherapp.domain.weather.WeatherData
import com.royal.tiger.androidweatherapp.domain.weather.WeatherInfo
import com.royal.tiger.androidweatherapp.domain.weather.WeatherType
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

private data class IndexedWeatherData(
    val index: Int,
    val data: WeatherData
)

fun WeatherDataDto.toWeatherDataMap(): Map<Int, List<WeatherData>> {
    return times.mapIndexed { index, time ->
        val temperature = temperatures[index]
        val humidity = humidities[index]
        val windSpeed = windSpeeds[index]
        val pressure = pressures[index]
        val weatherCode = weatherCodes[index]

        val weatherData = WeatherData(
            time = LocalDateTime.parse(time, DateTimeFormatter.ISO_DATE_TIME),
            temperature = temperature,
            humidity = humidity,
            windSpeed = windSpeed,
            pressure = pressure,
            weatherType = WeatherType.parseFromWMO(weatherCode)
        )

        IndexedWeatherData(
            index = index,
            data = weatherData
        )
    }.groupBy {
        it.index / 24
    }.mapValues {
        it.value.map { data -> data.data }
    }
}

fun WeatherDto.toWeatherInfo(): WeatherInfo {
    val weatherDataMap = weatherData.toWeatherDataMap()
    val currentData = weatherDataMap[0]?.find {
        val currentHour = LocalDateTime.now().hour
        if(currentHour < 30) it.time.hour == currentHour else it.time.hour == currentHour + 1
    }

    return WeatherInfo(
        perDayWeatherData = weatherDataMap,
        currentWeatherData = currentData
    )
}