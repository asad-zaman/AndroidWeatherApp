package com.royal.tiger.androidweatherapp.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.time.format.DateTimeFormatter

@Composable
fun TodayWeatherForecast(
    state: WeatherState,
    modifier: Modifier = Modifier
) {
    state.data?.perDayWeatherData?.get(0)?.let { hourlyWeatherItems ->
        Column(modifier = modifier.padding(16.dp)) {
            Text(
                text = "Today",
                color = Color.White,
                fontSize = 20.sp,
            )
            Spacer(modifier = modifier.height(10.dp))
            LazyRow(
                state = rememberLazyListState(),
                modifier = modifier,
            ) {
                items(hourlyWeatherItems) { data ->
                    HourlyWeatherDisplay(
                        hour = data.time.format(DateTimeFormatter.ofPattern("hh:mm a")),
                        icon = data.weatherType.icon,
                        temperature = data.temperature,
                        modifier = modifier.padding(horizontal = 10.dp)
                    )
                }
            }
        }
    }
}