package com.royal.tiger.androidweatherapp.presentation

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp

@Composable
fun HourlyWeatherDisplay(
    hour: String,
    @DrawableRes icon: Int,
    temperature: Double,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier,
    ) {
        Text(
            text = hour,
            color = Color.White,
        )
        Spacer(modifier = modifier.height(5.dp))
        Icon(
            imageVector = ImageVector.vectorResource(id = icon),
            contentDescription = null,
            modifier = modifier.size(35.dp),
            tint = Color.White,
        )
        Spacer(modifier = modifier.height(5.dp))
        Text(
            text = "$temperature°C",
            color = Color.White,
        )
    }
}