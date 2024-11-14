package com.ajverma.jetweatherapp.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface GeoCodingApi {
    @GET("v1/search")
    suspend fun getWeatherByCity(
        @Query("name") city: String?
    ): GeocodingResult
}