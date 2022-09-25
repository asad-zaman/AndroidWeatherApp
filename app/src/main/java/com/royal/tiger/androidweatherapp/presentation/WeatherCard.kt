package com.royal.tiger.androidweatherapp.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.time.format.DateTimeFormatter
import kotlin.math.roundToInt
import com.royal.tiger.androidweatherapp.R

@Composable
fun WeatherCard(
    state: WeatherState,
    background: Color,
    modifier: Modifier = Modifier
) {
    state.data?.currentWeatherData?.let { data ->
        Card(
            shape = RoundedCornerShape(10.dp),
            colors = CardDefaults.cardColors(
                containerColor = background
            ),
            modifier = modifier.padding(all = 16.dp)
        ) {
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = "Today ${data.time.format(DateTimeFormatter.ofPattern("HH:mm"))}",
                    modifier = modifier.align(Alignment.End),
                    color = Color.White,
                )
                Spacer(modifier = modifier.height(16.dp))
                Image(
                    painter = painterResource(id = data.weatherType.icon),
                    contentDescription = null,
                    modifier = modifier.width(200.dp)
                )
                Spacer(modifier = modifier.height(16.dp))
                Text(
                    text = "${ data.temperature } °C",
                    fontSize = 48.sp,
                    color = Color.White
                )
                Spacer(modifier = modifier.height(16.dp))
                Text(
                    text = data.weatherType.description,
                    fontSize = 18.sp,
                    color = Color.White
                )
                Spacer(modifier = modifier.height(16.dp))
                Row(
                    modifier = modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround,
                ) {
                    WeatherDataDisplay(
                        value = data.pressure.roundToInt(),
                        unit = "hpa",
                        icon = R.drawable.ic_pressure,
                    )
                    WeatherDataDisplay(
                        value = data.humidity.roundToInt(),
                        unit = "%",
                        icon = R.drawable.ic_drop,
                    )
                    WeatherDataDisplay(
                        value = data.windSpeed.roundToInt(),
                        unit = "km/h",
                        icon = R.drawable.ic_wind,
                    )
                }
            }
        }
    }
}