package com.ajverma.jetweatherapp.presentation.ui.screens.home

import androidx.lifecycle.ViewModel
import com.ajverma.jetweatherapp.domain.location.LocationTracker
import com.ajverma.jetweatherapp.domain.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val repository: WeatherRepository,
    private val currentLocation: LocationTracker
): ViewModel() {
}