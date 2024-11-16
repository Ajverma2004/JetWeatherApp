package com.ajverma.jetweatherapp.data.remote

data class Geocoding(
    val generationtime_ms: Double,
    val results: List<GeocodingResult>
)