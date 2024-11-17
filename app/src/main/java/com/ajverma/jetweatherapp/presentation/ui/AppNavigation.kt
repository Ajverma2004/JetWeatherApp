package com.ajverma.jetweatherapp.presentation.ui

import androidx.activity.viewModels
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ajverma.jetweatherapp.database.FavouriteCity
import com.ajverma.jetweatherapp.domain.util.AppScaffold
import com.ajverma.jetweatherapp.presentation.ui.screens.favourite.FavouriteCityScreen
import com.ajverma.jetweatherapp.presentation.ui.screens.favourite.FavouriteCityViewModel
import com.ajverma.jetweatherapp.presentation.ui.screens.home.HomeScreen
import com.ajverma.jetweatherapp.presentation.ui.screens.home.HomeScreenViewModel

@Composable
fun AppNavigation(
    modifier: Modifier = Modifier,
    viewModelFav: FavouriteCityViewModel,
    viewModelHome: HomeScreenViewModel
) {
    val navController = rememberNavController()
    var city by remember { mutableStateOf("") }

    NavHost(
        navController = navController, startDestination = "home"
    ) {
        composable("home") {
            AppScaffold(
                isHomeScreen = true,
                title = "Weather App",
                initialTrailingIcon = Icons.Default.FavoriteBorder,
                navigationIcon = Icons.Default.ArrowBack,
                trailingAction = {
                    if (city.isNotBlank()) {
                        viewModelFav.upsertFavCity(FavouriteCity(city = city))
                    }
                },
                trailingIcon2 = Icons.Default.MoreVert,
                trailingMenuItems = listOf("Favourites"),
                trailingAction2 = { selectedItems ->
                    when (selectedItems) {
                        "Favourites" -> navController.navigate("Fav")
                    }
                },
            ) { paddingValues ->
                HomeScreen(
                    data = viewModelHome.state,
                    viewModel = viewModelHome,
                    modifier = paddingValues,
                    onCityNameChange = { updatedCity ->
                        city = updatedCity
                    }
                )
            }
        }

        composable("Fav") {
            FavouriteCityScreen(
                favViewModel = viewModelFav,
                navController = navController
            )
        }

    }
}

