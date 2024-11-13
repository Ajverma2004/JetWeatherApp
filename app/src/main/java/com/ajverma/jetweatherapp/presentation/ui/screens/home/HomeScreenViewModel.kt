package com.ajverma.jetweatherapp.presentation.ui.screens.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ajverma.jetweatherapp.domain.location.LocationTracker
import com.ajverma.jetweatherapp.domain.repository.WeatherRepository
import com.ajverma.jetweatherapp.domain.util.Resource
import com.ajverma.jetweatherapp.presentation.ui.screens.WeatherState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val repository: WeatherRepository,
    private val currentLocation: LocationTracker
): ViewModel() {

    var state by mutableStateOf(WeatherState())
        private set

    fun loadWeatherInfo(){
        viewModelScope.launch {
            state = state.copy(
                weatherData = null,
                isLoading = true,
                error = null
            )

            currentLocation.getCurrentLocation()?.let { location ->
                val result = repository.getWeather(location.latitude, location.longitude)

                when (result){
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
}