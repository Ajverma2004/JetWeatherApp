package com.ajverma.jetweatherapp.presentation.ui.screens

import com.ajverma.jetweatherapp.domain.weather.WeatherInfo
import java.lang.Error

data class WeatherState<T> (
    val weatherData: T? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)