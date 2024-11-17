package com.ajverma.jetweatherapp.presentation.ui.screens.favourite

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.ajverma.jetweatherapp.database.FavouriteCity
import com.ajverma.jetweatherapp.domain.util.AppScaffold
import com.ajverma.jetweatherapp.ui.theme.ColorBackground
import com.ajverma.jetweatherapp.ui.theme.ColorSurface

@Composable
fun FavouriteCityScreen(
    modifier: Modifier = Modifier,
    favViewModel: FavouriteCityViewModel,
    navController: NavController
) {

    val cityList = favViewModel.favCityList.collectAsState().value

    AppScaffold(
        isHomeScreen = false,
        title = "Favourite Cities",
        navigationIcon = Icons.Default.ArrowBack,
        navigationAction = {
            navController.popBackStack()
        }
    ) { paddingValues ->
        FavCities(
            modifier = paddingValues,
            cityList = cityList,
            favViewModel = favViewModel
        )
    }
}

@Composable
fun FavCities(
    modifier: Modifier = Modifier,
    cityList: List<FavouriteCity>,
    favViewModel: FavouriteCityViewModel
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(ColorBackground)
    ){
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LazyColumn {
                items(cityList){ city ->
                    CityItems(
                        city = city,
                        onDeleteClick = {
                            favViewModel.deleteFavCity(city)
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun CityItems(
    modifier: Modifier = Modifier,
    city: FavouriteCity,
    onDeleteClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clip(RoundedCornerShape(15.dp))
            .background(ColorSurface)
        ,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Text(
            text = city.city.replaceFirstChar { it.uppercase() },
            fontSize = 25.sp,
            fontWeight = FontWeight.Black,
            modifier = Modifier.padding(16.dp).padding(vertical = 10.dp)
        )

        IconButton(onClick = onDeleteClick) {
            Icon(
                imageVector = Icons.Filled.Delete,
                contentDescription = "delete icon",
                modifier = Modifier.size(27.dp)
            )
        }
    }
}