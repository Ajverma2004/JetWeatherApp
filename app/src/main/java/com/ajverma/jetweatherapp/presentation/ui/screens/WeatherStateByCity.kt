package com.ajverma.jetweatherapp.presentation.ui.screens

import com.ajverma.jetweatherapp.data.remote.GeocodingResult

data class WeatherStateByCity(
    val weatherData: GeocodingResult? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)