package com.royal.tiger.androidweatherapp.presentation

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.royal.tiger.androidweatherapp.presentation.ui.theme.DeepBlue
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import kotlin.math.ceil
import kotlin.math.roundToInt

@Composable
fun HourlyWeatherDisplay(
    time: LocalDateTime,
    @DrawableRes icon: Int,
    temperature: Double,
    modifier: Modifier = Modifier
) {

    val currentTime = time.hour == LocalDateTime.now().hour

    Card(
        colors = CardDefaults.cardColors(
            containerColor = if(currentTime) DeepBlue else Color.Transparent
        ),
        modifier = modifier
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(10.dp)
        ) {
            Text(
                text = time.format(DateTimeFormatter.ofPattern("hh a")),
                color = Color.White,
            )
            Spacer(modifier = modifier.height(5.dp))
            Image(
                imageVector = ImageVector.vectorResource(id = icon),
                contentDescription = null,
                modifier = modifier.size(35.dp),
            )
            Spacer(modifier = modifier.height(5.dp))
            Text(
                text = "${ceil(temperature).roundToInt()}°C",
                color = Color.White,
            )
        }
    }
}