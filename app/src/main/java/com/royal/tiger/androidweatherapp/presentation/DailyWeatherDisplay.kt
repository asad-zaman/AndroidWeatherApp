package com.royal.tiger.androidweatherapp.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.royal.tiger.androidweatherapp.domain.weather.WeatherData
import java.time.format.DateTimeFormatter
import kotlin.math.ceil
import kotlin.math.roundToInt
import com.royal.tiger.androidweatherapp.R

@Composable
fun DailyWeatherDisplay(
    data: WeatherData,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth().padding(vertical = 15.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = data.time.format(DateTimeFormatter.ofPattern("EEEE")),
            color = Color.White,
            modifier = modifier.width(100.dp),
            fontSize = 18.sp
        )
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                imageVector = ImageVector.vectorResource(id = data.weatherType.icon),
                contentDescription = null,
                modifier = modifier.size(35.dp)
            )
            Spacer(modifier = modifier.height(5.dp))
            Text(
                text = "${ceil(data.temperature).roundToInt()}°C",
                color = Color.White,
            )
        }
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_drop),
                contentDescription = null,
                tint = Color.White,
                modifier = modifier.size(35.dp)
            )
            Spacer(modifier = modifier.height(5.dp))
            Text(
                text = "${data.humidity.roundToInt()}%",
                color = Color.White,
            )
        }
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_wind),
                contentDescription = null,
                tint = Color.White,
                modifier = modifier.size(35.dp)
            )
            Spacer(modifier = modifier.height(5.dp))
            Text(
                text = "${data.windSpeed.roundToInt()}km/h",
                color = Color.White,
            )
        }
    }
}