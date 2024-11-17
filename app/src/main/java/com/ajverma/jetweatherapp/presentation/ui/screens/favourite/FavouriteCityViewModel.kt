package com.ajverma.jetweatherapp.presentation.ui.screens.favourite

import androidx.lifecycle.ViewModel
import com.ajverma.jetweatherapp.database.FavouriteCity
import com.ajverma.jetweatherapp.database.FavouriteCityDao
import dagger.hilt.android.lifecycle.HiltViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavouriteCityViewModel @Inject constructor(
    private val dao: FavouriteCityDao
):ViewModel() {

    private val _favCityList = MutableStateFlow<List<FavouriteCity>>(emptyList())
    val favCityList = _favCityList.asStateFlow()


    init {
        fetchFavouriteCities()
    }

    private fun fetchFavouriteCities() {
        viewModelScope.launch {
            dao.getAllFavouriteCity().collect { cities ->
                _favCityList.value = cities
            }
        }
    }

    fun upsertFavCity(city: FavouriteCity){
        viewModelScope.launch {
            dao.upsertFavouriteCity(city)
        }
    }

    fun deleteFavCity(city: FavouriteCity){
        viewModelScope.launch {
            dao.deleteFavouriteCity(city)
        }
    }
}