package com.royal.tiger.androidweatherapp.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.time.LocalDateTime

@Composable
fun WeeklyWeatherSummery(
    state: WeatherState,
    modifier: Modifier = Modifier
) {
    state.data?.perDayWeatherData?.let { weatherMap ->
        Column(modifier = modifier
            .fillMaxWidth()
            .padding(10.dp)) {
            Text(
                text = "This Week",
                color = Color.White,
                fontSize = 20.sp,
                modifier = modifier.padding(horizontal = 10.dp),
                style = TextStyle(textDecoration = TextDecoration.Underline)
            )
            Spacer(modifier = modifier.height(10.dp))
            LazyColumn(modifier = modifier.padding(10.dp)) {
                items(weatherMap.size) { index ->
                    val data = weatherMap[index]?.find { it.time.hour == LocalDateTime.now().hour }
                    DailyWeatherDisplay(data = data!!)
                }
            }
        }
    }
}