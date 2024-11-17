package com.ajverma.jetweatherapp.presentation.ui.utils

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ajverma.jetweatherapp.R
import com.ajverma.jetweatherapp.domain.weather.WeatherInfo
import com.ajverma.jetweatherapp.presentation.ui.screens.home.WeatherState
import com.ajverma.jetweatherapp.ui.theme.ColorAirQualityIconTitle
import com.ajverma.jetweatherapp.ui.theme.ColorSurface
import com.ajverma.jetweatherapp.ui.theme.ColorTextPrimary
import com.ajverma.jetweatherapp.ui.theme.ColorTextPrimaryVariant
import kotlin.math.roundToInt


@OptIn(ExperimentalLayoutApi::class)
@Composable
fun AirQualityData(
    modifier: Modifier = Modifier,
    state: WeatherState<WeatherInfo>
) {

    state.weatherData?.currentWeatherData?.let {
        Surface(
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp),
            shape = RoundedCornerShape(32.dp),
            color = ColorSurface
        ) {
            Column(
                modifier = Modifier.padding(
                    vertical = 18.dp,
                    horizontal = 24.dp
                ),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                AirQualityHeader()

                FlowRow(
                    modifier = Modifier.fillMaxWidth(),
                    maxItemsInEachRow = 3,
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    state.weatherData?.currentWeatherData?.let { data->
                        AirQualityInfo(
                            icon = ImageVector.vectorResource(id = R.drawable.ic_drop),
                            title = "Humidity",
                            value = data.humidity.toInt(),
                            unit = "%",
                        )
                        AirQualityInfo(
                            icon = ImageVector.vectorResource(id = R.drawable.ic_pressure),
                            title = "Pressure",
                            value = data.pressure.roundToInt(),
                            unit = "hpa"
                        )
                        AirQualityInfo(
                            icon = ImageVector.vectorResource(id = R.drawable.ic_wind),
                            title = "Wind Speed",
                            value = data.windSpeed.roundToInt(),
                            unit = "km/h"
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun AirQualityHeader(
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_air_quality_header),
                contentDescription = null,
                modifier = Modifier.size(32.dp),
                tint = ColorAirQualityIconTitle
            )
            Text(
                text = "Air Quality",
                style = MaterialTheme.typography.titleMedium.copy(
                    fontSize = 18.sp
                ),

            )
        }

    }
}


@Composable
private fun AirQualityInfo(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    title: String,
    value: Int,
    unit: String
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly,
    ) {

        Icon(
            imageVector = icon,
            contentDescription = null,
            modifier = Modifier.size(32.dp),
            tint = ColorAirQualityIconTitle
        )

        Column(
            horizontalAlignment = Alignment.Start,
            modifier = Modifier.padding(start = 5.dp)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.labelLarge,
                color = ColorTextPrimaryVariant,
                fontSize = 15.sp,
                fontWeight = FontWeight.Black
            )
            Text(
                text = "$value $unit",
                style = MaterialTheme.typography.labelMedium,
                color = ColorTextPrimary,
                fontSize = 13.sp,

            )
        }
    }
}