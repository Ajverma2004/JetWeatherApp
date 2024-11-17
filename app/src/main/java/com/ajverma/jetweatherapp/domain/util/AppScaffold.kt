package com.ajverma.jetweatherapp.domain.util

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppScaffold(
    modifier: Modifier = Modifier,
    isHomeScreen: Boolean,
    title: String,
    navigationIcon: ImageVector? = null,
    navigationAction: (() -> Unit)? = null,
    initialTrailingIcon: ImageVector? = null,
    alternateTrailingIcon: ImageVector? = Icons.Filled.Favorite,
    trailingAction: (() -> Unit)? = null,
    trailingIcon2: ImageVector? = null,
    trailingMenuItems: List<String> = emptyList(),
    trailingAction2: ((String) -> Unit)? = null,
    screen: @Composable (paddingValues: Modifier) -> Unit,
) {
    var trailingIcon by remember {
        mutableStateOf(initialTrailingIcon)
    }

    var clicked by remember {
        mutableStateOf(false)
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = title,
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp,
                        modifier = Modifier.fillMaxWidth()
                    )
                },
                navigationIcon = {
                    if (!isHomeScreen){
                        navigationIcon?.let {
                            Icon(
                                imageVector = it,
                                contentDescription = null,
                                modifier = Modifier.clickable {
                                    navigationAction?.invoke()
                                }.padding(start = 10.dp, end = 10.dp)
                            )
                        }
                    }
                },
                actions = {
                    Row(
                        modifier = Modifier,
                        horizontalArrangement = Arrangement.End,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        if (isHomeScreen){
                            trailingIcon?.let {
                                Icon(
                                    imageVector = it,
                                    contentDescription = "Trailing Icon 1",
                                    modifier = Modifier
                                        .clickable {
                                            clicked = !clicked
                                            trailingIcon = if (clicked) alternateTrailingIcon else initialTrailingIcon
                                            trailingAction?.invoke()
                                        }
                                        .padding(end = 10.dp),
                                    tint = if (clicked) Color.Red else Color.Unspecified
                                )
                            }

                            trailingIcon2?.let {
                                DropDownMenu(
                                    menuIcon = it,
                                    menuItems = trailingMenuItems,
                                    onMenuItemClick = { selectedItem ->
                                        trailingAction2?.invoke(selectedItem)
                                    }
                                )
                            }
                        }
                    }
                }
            )
        }
    ) { paddingValues ->
        screen(Modifier.padding(paddingValues))
    }
}
