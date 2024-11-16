package com.ajverma.jetweatherapp.presentation.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ajverma.jetweatherapp.domain.weather.WeatherData
import com.ajverma.jetweatherapp.domain.weather.WeatherInfo
import com.ajverma.jetweatherapp.presentation.ui.screens.WeatherState
import com.ajverma.jetweatherapp.presentation.ui.utils.AirQualityData
import com.ajverma.jetweatherapp.presentation.ui.utils.HourlyForecastData
import com.ajverma.jetweatherapp.presentation.ui.utils.WeatherCard
import com.ajverma.jetweatherapp.ui.theme.ColorBackground

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    data: WeatherState<WeatherInfo>
) {

    Box(modifier = Modifier
        .fillMaxSize()
        .background(ColorBackground)
        .verticalScroll(rememberScrollState())){
        Column(
            modifier = modifier
                .fillMaxSize()
        ) {

            if (data.isLoading){
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }

            data.error?.let {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "oops! something went wrong${data.error}")
                }
            }

            data.weatherData?.let {
                WeatherCard(
                    data = data
                )

                Spacer(modifier = Modifier.height(20.dp))

                AirQualityData(state = data)

                Spacer(modifier = Modifier.height(15.dp))

                HourlyForecastData(
                    data = data,
                    modifier = Modifier
                        .height(200.dp)
                        .padding(horizontal = 16.dp)
                        .border(1.dp, Color.LightGray)
                )
            }
        }
    }
}