package com.ajverma.jetweatherapp.presentation.ui.utils

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ajverma.jetweatherapp.domain.weather.WeatherInfo
import com.ajverma.jetweatherapp.presentation.ui.screens.WeatherState
import com.ajverma.jetweatherapp.ui.theme.ColorGradient1
import com.ajverma.jetweatherapp.ui.theme.ColorGradient2
import com.ajverma.jetweatherapp.ui.theme.ColorGradient3
import com.ajverma.jetweatherapp.ui.theme.ColorTextSecondary
import com.ajverma.jetweatherapp.ui.theme.ColorTextSecondaryVariant
import java.time.format.DateTimeFormatter

@Composable
fun WeatherCard(
    modifier: Modifier = Modifier,
    data: WeatherState<WeatherInfo>
) {

    data.weatherData?.currentWeatherData?.let { data ->
        Box(
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp)
                .clip(RoundedCornerShape(32.dp))
                .shadow(15.dp)
                .background(
                    brush = Brush.linearGradient(
                        0f to ColorGradient1,
                        0.5f to ColorGradient2,
                        1f to ColorGradient3
                    ),
                    shape = RoundedCornerShape(32.dp)
                )
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                //image and description
                Column(
                    verticalArrangement = Arrangement.SpaceEvenly,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(10.dp)
                ) {
                    Image(
                        painter = painterResource(id = data.weatherType.iconRes),
                        contentDescription = "weather icon",
                        modifier = Modifier.size(175.dp)
                    )

                    Text(
                        text = data.weatherType.weatherDesc,
                        style = MaterialTheme.typography.titleLarge,
                        color = ColorTextSecondary,
                        fontWeight = FontWeight.Medium,
                        fontSize = 30.sp
                    )

                    Text(
                        text = data.time.format(
                            DateTimeFormatter.ofPattern("EEEE, dd MMM")
                        ),
                        style = MaterialTheme.typography.bodyMedium,
                        color = ColorTextSecondaryVariant,
                        modifier = Modifier
                            .padding(bottom = 24.dp),
                        fontSize = 18.sp
                    )

                }

                // temperature
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(12.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(10.dp)
                    ) {
                        Text(
                            text = data.temperatureCelsius.toString(),
                            letterSpacing = 0.sp,
                            style = TextStyle(
                                brush = Brush.verticalGradient(
                                    0f to Color.White,
                                    1f to Color.White.copy(alpha = 0.3f)
                                ),
                                fontSize = 75.sp,
                                fontWeight = FontWeight.Black
                            ),
                            modifier = Modifier.padding(end = 10.dp)
                        )
                        Text(
                            text = "°",
                            style = TextStyle(
                                brush = Brush.verticalGradient(
                                    0f to Color.White,
                                    1f to Color.White.copy(alpha = 0.3f)
                                ),
                                fontSize = 75.sp,
                                fontWeight = FontWeight.Light,
                            ),
                            modifier = Modifier.padding(top = 2.dp)
                        )
                    }
                }
            }
        }
    }
}