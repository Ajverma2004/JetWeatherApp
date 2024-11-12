package com.ajverma.jetweatherapp.domain.repository

import com.ajverma.jetweatherapp.data.remote.WeatherDto
import com.ajverma.jetweatherapp.domain.util.Resource
import com.ajverma.jetweatherapp.domain.weather.WeatherData
import com.ajverma.jetweatherapp.domain.weather.WeatherInfo


interface WeatherRepository {
    suspend fun getWeather(lat: Double, long: Double): Resource<WeatherInfo>
}