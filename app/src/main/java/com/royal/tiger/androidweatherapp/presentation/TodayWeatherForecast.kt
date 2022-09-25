package com.royal.tiger.androidweatherapp.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Composable
fun TodayWeatherForecast(
    state: WeatherState,
    modifier: Modifier = Modifier
) {
    state.data?.perDayWeatherData?.get(0)?.let { hourlyWeatherItems ->
        val scrollState = rememberLazyListState()

        Column(modifier = modifier.padding(horizontal = 10.dp)) {
            Text(
                text = "Today",
                color = Color.White,
                fontSize = 20.sp,
                modifier = modifier.padding(horizontal = 10.dp),
                style = TextStyle(textDecoration = TextDecoration.Underline)
            )
            Spacer(modifier = modifier.height(10.dp))
            LazyRow(
                state = scrollState,
                modifier = modifier.padding(10.dp),
            ) {
                items(hourlyWeatherItems) { data ->
                    HourlyWeatherDisplay(
                        time = data.time,
                        icon = data.weatherType.icon,
                        temperature = data.temperature,
                        modifier = modifier.padding(horizontal = 5.dp)
                    )
                }
            }

            LaunchedEffect(true) {
                scrollState.scrollToItem(hourlyWeatherItems.indexOfFirst {
                    it.time.hour == LocalDateTime.now().hour
                })
            }
        }
    }
}