package com.ajverma.jetweatherapp.presentation.ui.utils

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ajverma.jetweatherapp.domain.weather.WeatherData
import com.ajverma.jetweatherapp.domain.weather.WeatherInfo
import com.ajverma.jetweatherapp.presentation.ui.screens.WeatherState
import com.ajverma.jetweatherapp.ui.theme.ColorGradient1
import com.ajverma.jetweatherapp.ui.theme.ColorGradient2
import com.ajverma.jetweatherapp.ui.theme.ColorGradient3
import com.ajverma.jetweatherapp.ui.theme.ColorTextPrimary
import java.time.format.DateTimeFormatter


@Composable
fun HourlyForecastData(
    modifier: Modifier = Modifier,
    data: WeatherState<WeatherInfo>
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = "Hourly forecast",
            style = MaterialTheme.typography.titleLarge,
            color = ColorTextPrimary,
            fontWeight = FontWeight.Bold,
            fontSize = 28.sp,
            modifier = Modifier.padding(bottom = 7.dp, start = 10.dp)
        )
        data.weatherData?.weatherDataPerDay?.get(0)?.let { data ->
            LazyRow(
                content = {
                    items(data){ weatherData ->
                        HourlyForecastRow(data = weatherData)
                    }
                }
            )
        }
    }
}


@Composable
fun HourlyForecastRow(
    modifier: Modifier = Modifier,
    data: WeatherData
) {
    Box(
        modifier = Modifier
            .width(130.dp)
            .height(230.dp)
            .padding(5.dp)
            .background(
                shape = RoundedCornerShape(percent = 50),
                brush = Brush.linearGradient(
                    0f to ColorGradient1,
                    0.5f to ColorGradient2,
                    1f to ColorGradient3
                )
            )

        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(7.dp)
                    .padding(10.dp),
                verticalArrangement = Arrangement.SpaceAround,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = data.time.format(
                        DateTimeFormatter.ofPattern("HH:mm")
                    ),
                    style = MaterialTheme.typography.labelLarge,
                    fontSize = 19.sp,
                    color = ColorTextPrimary,
                    fontWeight = FontWeight.SemiBold
                )

                Image(painter = painterResource(id = data.weatherType.iconRes), contentDescription = null)

                Text(
                    text = "${data.temperatureCelsius}°C",
                    style = MaterialTheme.typography.labelLarge,
                    fontSize = 19.sp,
                    color = ColorTextPrimary,
                    fontWeight = FontWeight.SemiBold
                )
            }
    }
}
