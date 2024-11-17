package com.ajverma.jetweatherapp.presentation.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.ajverma.jetweatherapp.domain.util.CustomTextField
import com.ajverma.jetweatherapp.domain.weather.WeatherInfo
import com.ajverma.jetweatherapp.presentation.ui.utils.AirQualityData
import com.ajverma.jetweatherapp.presentation.ui.utils.HourlyForecastData
import com.ajverma.jetweatherapp.presentation.ui.utils.WeatherCard
import com.ajverma.jetweatherapp.ui.theme.ColorBackground

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    data: WeatherState<WeatherInfo>,
    viewModel: HomeScreenViewModel,
    onCityNameChange: (String) -> Unit
    ) {
    var city by remember {
        mutableStateOf("")
    }
    onCityNameChange(city)
    Box(modifier = modifier
        .fillMaxSize()
        .background(ColorBackground)
        .verticalScroll(rememberScrollState())
    ){
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Top
        ) {

            CustomTextField(
                trailingIcon = Icons.Default.Search,
                text = city,
                onTextChange = { city = it },
                singleLine = true,
                onTrailingIconClick = {
                    viewModel.loadWeatherInfoByCity(city)
                },
                modifier = Modifier
                    .padding(bottom = 5.dp)
            )

            data.weatherData?.let {

                WeatherCard(
                    data = data
                )


                AirQualityData(state = data)



                HourlyForecastData(
                    data = data,
                    modifier = Modifier
                        .height(200.dp)
                        .padding(horizontal = 16.dp)
                        .border(1.dp, Color.LightGray)
                )
            }

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
        }
    }
}