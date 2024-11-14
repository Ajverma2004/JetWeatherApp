package com.ajverma.jetweatherapp.data.Repository

import com.ajverma.jetweatherapp.data.mappers.toWeatherInfo
import com.ajverma.jetweatherapp.data.remote.GeoCodingApi
import com.ajverma.jetweatherapp.data.remote.GeocodingResult
import com.ajverma.jetweatherapp.data.remote.WeatherApi
import com.ajverma.jetweatherapp.data.remote.WeatherDto
import com.ajverma.jetweatherapp.domain.repository.WeatherRepository
import com.ajverma.jetweatherapp.domain.util.Resource
import com.ajverma.jetweatherapp.domain.weather.WeatherInfo
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val api: WeatherApi,
    private val geoApi: GeoCodingApi

): WeatherRepository {
    override suspend fun getWeather(lat: Double, long: Double): Resource<WeatherInfo> {
        return try {
            Resource.Success(
                data = api.getWeather(
                    lat = lat,
                    long = long
                ).toWeatherInfo()
            )
        }
        catch (e: Exception){
            e.printStackTrace()
            Resource.Error(e.message.toString())
        }
    }

    override suspend fun getWeatherByCity(city: String?): Resource<GeocodingResult> {
        return try {
            Resource.Success(
                data = geoApi.getWeatherByCity(city)
            )
        } catch (e: Exception){
            e.printStackTrace()
            Resource.Error(e.message.toString())
        }
    }
}