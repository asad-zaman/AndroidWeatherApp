package com.royal.tiger.androidweatherapp.domain.weather

import androidx.annotation.DrawableRes
import com.royal.tiger.androidweatherapp.R

sealed class WeatherType(
    val description: String,
    @DrawableRes val icon: Int
) {
    object ClearSky: WeatherType(description = "Clear sky", icon = R.drawable.ic_sunny)
    object MainlyClear: WeatherType(description = "Mainly clear", icon = R.drawable.ic_cloudy)
    object PartlyCloudy: WeatherType(description = "Partly cloudy", icon = R.drawable.ic_cloudy)
    object Overcast: WeatherType(description = "Overcast", icon = R.drawable.ic_cloudy)
    object Foggy: WeatherType(description = "Foggy", icon = R.drawable.ic_very_cloudy)
    object DepositingRimeFog: WeatherType(description = "depositing rime fog", icon = R.drawable.ic_very_cloudy)
    object LightDrizzle: WeatherType(description = "Light drizzle", icon = R.drawable.ic_rain_shower)
    object ModerateDrizzle: WeatherType(description = "Moderate drizzle", icon = R.drawable.ic_rain_shower)
    object DenseDrizzle: WeatherType(description = "Dense drizzle", icon = R.drawable.ic_rain_shower)
    object LightFreezingDrizzle: WeatherType(description = "Light freezing drizzle", icon = R.drawable.ic_snowy_rainy)
    object DenseFreezingDrizzle: WeatherType(description = "Dense freezing drizzle", icon = R.drawable.ic_snowy_rainy)
    object SlightRain: WeatherType(description = "Slight rain", icon = R.drawable.ic_rainy)
    object ModerateRain: WeatherType(description = "Moderate rain", icon = R.drawable.ic_rainy)
    object HeavyRain: WeatherType(description = "Heavy rain", icon = R.drawable.ic_rainy)
    object LightFreezingRain: WeatherType(description = "Light freezing rain", icon = R.drawable.ic_snowy_rainy)
    object HeavyFreezingRain: WeatherType(description = "Heavy freezing rain", icon = R.drawable.ic_snowy_rainy)
    object SlightSnowFall: WeatherType(description = "Slight snow fall", icon = R.drawable.ic_heavy_snow)
    object ModerateSnowFall: WeatherType(description = "Moderate snow fall", icon = R.drawable.ic_heavy_snow)
    object HeavySnowFall: WeatherType(description = "Heavy snow fall", icon = R.drawable.ic_heavy_snow)
    object SnowGrains: WeatherType(description = "Snow grains", icon = R.drawable.ic_heavy_snow)
    object SlightRainShowers: WeatherType(description = "Slight Rain showers", icon = R.drawable.ic_rain_shower)
    object ModerateRainShowers: WeatherType(description = "Moderate Rain showers", icon = R.drawable.ic_rain_shower)
    object ViolentRainShowers: WeatherType(description = "Violent Rain showers", icon = R.drawable.ic_rain_shower)
    object SlightSnowShowers: WeatherType(description = "Slight Snow showers", icon = R.drawable.ic_snowy)
    object HeavySnowShowers: WeatherType(description = "Heavy Snow showers", icon = R.drawable.ic_rain_shower)
    object ModerateThunderstorm: WeatherType(description = "Moderate thunderstorm", icon = R.drawable.ic_thunder)
    object SlightHailThunderstorm: WeatherType(description = "Slight hail thunderstorm", icon = R.drawable.ic_rainy_thunder)
    object HeavyHailThunderstorm: WeatherType(description = "Heavy hail thunderstorm", icon = R.drawable.ic_rainy_thunder)

    companion object {
        fun parseFromWMO(code: Int) : WeatherType {
            return when(code) {
                0 -> ClearSky
                1 -> MainlyClear
                2 -> PartlyCloudy
                3 -> Overcast
                45 -> Foggy
                48 -> DepositingRimeFog
                51 -> LightDrizzle
                53 -> ModerateDrizzle
                55 -> DenseDrizzle
                56 -> LightFreezingDrizzle
                57 -> DenseFreezingDrizzle
                61 -> SlightRain
                63 -> ModerateRain
                65 -> HeavyRain
                66 -> LightFreezingRain
                67 -> HeavyFreezingRain
                71 -> SlightSnowFall
                73 -> ModerateSnowFall
                75 -> HeavySnowFall
                77 -> SnowGrains
                80 -> SlightRainShowers
                81 -> ModerateRainShowers
                82 -> ViolentRainShowers
                85 -> SlightSnowShowers
                86 -> HeavySnowShowers
                95 -> ModerateThunderstorm
                96 -> SlightHailThunderstorm
                99 -> HeavyHailThunderstorm
                else -> ClearSky
            }
        }
    }
}
