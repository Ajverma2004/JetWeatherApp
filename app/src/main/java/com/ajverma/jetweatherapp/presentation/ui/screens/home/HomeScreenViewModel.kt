package com.ajverma.jetweatherapp.presentation.ui.screens.home

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ajverma.jetweatherapp.domain.location.LocationTracker
import com.ajverma.jetweatherapp.domain.repository.WeatherRepository
import com.ajverma.jetweatherapp.domain.util.Resource
import com.ajverma.jetweatherapp.presentation.ui.screens.WeatherState
import com.ajverma.jetweatherapp.presentation.ui.screens.WeatherStateByCity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val repository: WeatherRepository,
    private val currentLocation: LocationTracker,
): ViewModel() {

    var state by mutableStateOf(WeatherState())
        private set

    var stateByCity by mutableStateOf(WeatherStateByCity())
        private set

    fun loadWeatherInfo(lat: Double? = null, long: Double? = null) {
        viewModelScope.launch {
            state = state.copy(
                weatherData = null,
                isLoading = true,
                error = null
            )

            val currentLatLong = if (lat == null || long == null) {
                currentLocation.getCurrentLocation()
            } else {
                null
            }

            currentLatLong?.let { location ->
                val latitude = lat ?: location.latitude
                val longitude = long ?: location.longitude
                val result = repository.getWeather(lat = latitude, long = longitude)

                when (result) {
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
            } ?: run {
                state = state.copy(
                    isLoading = false,
                    error = "Couldn't retrieve location. Make sure to Grant Permission or enable GPS"
                )
            }
        }
    }

    fun loadWeatherInfoByCity(city: String?) {
        viewModelScope.launch {
            stateByCity = stateByCity.copy(
                weatherData = null,
                isLoading = true,
                error = null
            )

            when (val result = repository.getWeatherByCity(city)) {
                is Resource.Success -> {
                    Log.d("API Response", "City Response: ${result.data}")
                    stateByCity = stateByCity.copy(
                        weatherData = result.data,
                        isLoading = false,
                        error = null
                    )

                    // Assuming the result contains latitude and longitude in the weather data
                    val latitude = result.data?.latitude
                    val longitude = result.data?.longitude

                    if (latitude != null && longitude != null) {
                        Log.d("Coordinates", "Latitude: $latitude, Longitude: $longitude")
                        // Call loadWeatherInfo with the retrieved latitude and longitude
                        loadWeatherInfo(lat = latitude, long = longitude)
                    } else {
                        stateByCity = stateByCity.copy(
                            isLoading = false,
                            error = "Unable to get latitude and longitude from city data."
                        )
                    }

                }
                is Resource.Error -> {
                    stateByCity = stateByCity.copy(
                        weatherData = null,
                        isLoading = false,
                        error = result.message
                    )
                }
            }
        }
    }
}
