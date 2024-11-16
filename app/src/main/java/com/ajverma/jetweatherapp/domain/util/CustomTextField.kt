

package com.ajverma.jetweatherapp.domain.util

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.ajverma.jetweatherapp.ui.theme.ColorGradient1
import com.ajverma.jetweatherapp.ui.theme.ColorGradient2
import com.ajverma.jetweatherapp.ui.theme.ColorGradient3

@Composable
fun CustomTextField(
    modifier: Modifier = Modifier,
    text: String,
    onTextChange: (String) -> Unit,
    keyboardType: KeyboardType = KeyboardType.Text,
    icon: ImageVector,
    search: () -> Unit,
) {
    var isFocused by remember { mutableStateOf(false) }

    val backgroundBrush =
        Brush.linearGradient(
            0f to ColorGradient1,
            0.5f to ColorGradient2,
            1f to ColorGradient3
        )

    Row(
        modifier = modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically

    ) {
        OutlinedTextField(
            value = text,
            onValueChange = { onTextChange(it) },
            placeholder = { Text(text = "Enter a city name") },
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
            textStyle = TextStyle(brush = backgroundBrush),
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(15.dp)),
            trailingIcon = {
                Icon(
                    imageVector = icon,
                    contentDescription = "icon",
                    modifier = Modifier
                        .clickable {
                            search()
                        }
                )
            }
        )
    }
}