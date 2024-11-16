package com.ajverma.jetweatherapp.domain.util

import android.view.inputmethod.InputMethodManager
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ajverma.jetweatherapp.ui.theme.ColorGradient1
import com.ajverma.jetweatherapp.ui.theme.ColorGradient2
import com.ajverma.jetweatherapp.ui.theme.ColorGradient3
import com.ajverma.jetweatherapp.ui.theme.ColorSurface

@Composable
fun CustomTextField(
    modifier: Modifier = Modifier,
    text: String,
    onTextChange: (String) -> Unit,
    keyboardType: KeyboardType = KeyboardType.Text,
    trailingIcon: ImageVector,
    onTrailingIconClick: () -> Unit,
    singleLine: Boolean = true
) {
    var isFocused by remember { mutableStateOf(false) }
    val focusManager = LocalFocusManager.current
    val focusRequester = remember { FocusRequester() }
    val context = LocalContext.current
    val view = LocalView.current
    val inputMethodManager = context.getSystemService(InputMethodManager::class.java)

    val defaultBrush = Brush.linearGradient(
        0f to ColorSurface,
        1f to ColorSurface
    )

    val focusedBrush = Brush.linearGradient(
        0f to ColorGradient1,
        0.5f to ColorGradient2,
        1f to ColorGradient3
    )

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable {
                focusManager.clearFocus() // Clear focus when clicking outside
            },
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        BasicTextField(
            value = text,
            onValueChange = { onTextChange(it) },
            singleLine = singleLine,
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
            textStyle = TextStyle(
                color = if (isFocused) Color.White else Color.Black,
                fontSize = 20.sp,
            ),
            cursorBrush = SolidColor(if (isFocused) Color.White else Color.Black),
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
                .background(if (isFocused) focusedBrush else defaultBrush)
                .border(
                    width = 2.dp,
                    brush = if (isFocused) focusedBrush else defaultBrush,
                    shape = RoundedCornerShape(12.dp)
                )
                .padding(horizontal = 16.dp, vertical = 12.dp)
                .clickable { isFocused = true }
                .focusRequester(focusRequester)
                .onFocusChanged { focusState ->
                    isFocused = focusState.isFocused
                },
            decorationBox = { innerTextField ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp, vertical = 12.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    if (text.isEmpty() && !isFocused) {
                        Text(
                            text = "Enter a city name",
                            color = Color.Gray,
                            fontSize = 20.sp
                        )
                    }
                    innerTextField() // Ensures the cursor and text input work correctly

                    Icon(
                        imageVector = trailingIcon,
                        contentDescription = "Search Icon",
                        modifier = Modifier
                            .clickable {
                                onTrailingIconClick()
                                focusManager.clearFocus()
                                inputMethodManager?.hideSoftInputFromWindow(
                                    view.windowToken,
                                    0
                                )
                            }
                            .padding(start = 8.dp)
                    )
                }
            }
        )
    }
}
