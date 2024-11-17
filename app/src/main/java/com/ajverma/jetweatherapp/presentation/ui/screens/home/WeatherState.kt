package com.ajverma.jetweatherapp.presentation.ui.screens.home

data class WeatherState<T> (
    val weatherData: T? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)