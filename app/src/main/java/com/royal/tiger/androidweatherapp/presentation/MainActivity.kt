package com.royal.tiger.androidweatherapp.presentation

import android.Manifest
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.royal.tiger.androidweatherapp.presentation.ui.theme.AndroidWeatherAppTheme
import com.royal.tiger.androidweatherapp.presentation.ui.theme.DarkBlue
import com.royal.tiger.androidweatherapp.presentation.ui.theme.DeepBlue
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: WeatherViewModel by viewModels()
    private lateinit var permissionLauncher: ActivityResultLauncher<Array<String>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        permissionLauncher = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) {
            viewModel.getWeatherInfo()
        }

        permissionLauncher.launch(
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION,
            )
        )

        setContent {
            AndroidWeatherAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Box(modifier = Modifier.fillMaxSize()) {
                        Column(modifier = Modifier.fillMaxSize()
                            .background(DarkBlue)) {
                            WeatherCard(
                                state = viewModel.state,
                                background = DeepBlue
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                            TodayWeatherForecast(state = viewModel.state)
                            Spacer(modifier = Modifier.height(16.dp))
                            WeeklyWeatherSummery(state = viewModel.state)
                        }

                        if(viewModel.state.loading) {
                            CircularProgressIndicator(
                                color = Color.Yellow,
                                modifier = Modifier.align(Alignment.Center)
                            )
                        }

                        viewModel.state.error?.let {
                            Text(
                                text = it,
                                color = Color.Red,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.align(Alignment.Center),
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    AndroidWeatherAppTheme {
        Surface {
            WeatherCard(
                state = WeatherState(),
                background = DeepBlue,
            )
        }
    }
}