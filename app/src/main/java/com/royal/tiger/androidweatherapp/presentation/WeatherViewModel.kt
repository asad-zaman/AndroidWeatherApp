package com.royal.tiger.androidweatherapp.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.royal.tiger.androidweatherapp.domain.location.LocationTracker
import com.royal.tiger.androidweatherapp.domain.repository.WeatherRepository
import com.royal.tiger.androidweatherapp.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val locationTracker: LocationTracker,
    private val weatherRepository: WeatherRepository
) : ViewModel() {
    var state by mutableStateOf(WeatherState())
        private set

    fun getWeatherInfo() {
        viewModelScope.launch {
            state = state.copy(
                loading = true,
                error = null
            )

            locationTracker.getCurrentLocation()?.let { location ->
                when(val result = weatherRepository.getWeatherData(lat = location.latitude, long = location.longitude)) {
                    is Resource.Success -> {
                        state = state.copy(
                            data = result.data,
                            loading = false,
                            error = null
                        )
                    }
                    is Resource.Error -> {
                        state = state.copy(
                            data = null,
                            loading = false,
                            error = null
                        )
                    }
                }
            } ?: kotlin.run {
                state = state.copy(
                    loading = false,
                    error = "Unable to retrieve location due to permission denied or gps disabled"
                )
            }
        }
    }
}