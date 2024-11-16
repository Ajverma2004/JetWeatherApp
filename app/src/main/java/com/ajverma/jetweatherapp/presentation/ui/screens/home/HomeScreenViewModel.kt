package com.ajverma.jetweatherapp.presentation.ui.screens.home

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ajverma.jetweatherapp.data.remote.Geocoding
import com.ajverma.jetweatherapp.domain.location.LocationTracker
import com.ajverma.jetweatherapp.domain.repository.WeatherRepository
import com.ajverma.jetweatherapp.domain.util.Resource
import com.ajverma.jetweatherapp.domain.weather.WeatherInfo
import com.ajverma.jetweatherapp.presentation.ui.screens.WeatherState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val repository: WeatherRepository,
    private val currentLocation: LocationTracker,
): ViewModel() {

    var state by mutableStateOf(WeatherState<WeatherInfo>())
        private set


    fun loadWeatherInfo(lat: Double? = null, long: Double? = null) {
        viewModelScope.launch {
            state = state.copy(
                weatherData = null,
                isLoading = true,
                error = null
            )

            // Check if lat and long are provided; if not, get the current location
            val location = if (lat != null && long != null) {
                null // Skip getting the current location as lat and long are provided
            } else {
                currentLocation.getCurrentLocation() // Retrieve current location
            }

            val latitude = lat ?: location?.latitude
            val longitude = long ?: location?.longitude

            if (latitude != null && longitude != null) {
                // Log to verify lat and long are correct
                Log.d("LoadWeatherInfo", "Latitude: $latitude, Longitude: $longitude")

                // Fetch weather data with the provided or retrieved coordinates
                when (val result = repository.getWeather(lat = latitude, long = longitude)) {
                    is Resource.Success -> {
                        state = state.copy(
                            weatherData = result.data,
                            isLoading = false,
                            error = null
                        )
                    }
                    is Resource.Error -> {
                        state = state.copy(
                            weatherData = null,
                            isLoading = false,
                            error = result.message
                        )
                    }
                }
            } else {
                state = state.copy(
                    isLoading = false,
                    error = "Couldn't retrieve location. Make sure to grant permission or enable GPS"
                )
                Log.d("LoadWeatherInfo", "Failed to get latitude and longitude")
            }
        }
    }

    fun loadWeatherInfoByCity(city: String?) {
        viewModelScope.launch {
            state = state.copy(
                weatherData = null,
                isLoading = true,
                error = null
            )

            when (val result = repository.getWeatherByCity(city)) {
                is Resource.Success -> {
                    Log.d("API Response", "City Response: ${result.data}")

                    // Assuming the result contains latitude and longitude in the weather data
                    val latitude = result.data?.results?.get(0)?.latitude
                    val longitude = result.data?.results?.get(0)?.longitude

                    if (latitude != null && longitude != null) {
                        Log.d("Coordinates", "Latitude: $latitude, Longitude: $longitude")
                        // Call loadWeatherInfo with the retrieved latitude and longitude
                        loadWeatherInfo(lat = latitude, long = longitude)
                    } else {
                        state = state.copy(
                            isLoading = false,
                            error = "Unable to get latitude and longitude from city data."
                        )
                    }

                }
                is Resource.Error -> {
                    state = state.copy(
                        weatherData = null,
                        isLoading = false,
                        error = result.message
                    )
                }
            }
        }
    }
}
