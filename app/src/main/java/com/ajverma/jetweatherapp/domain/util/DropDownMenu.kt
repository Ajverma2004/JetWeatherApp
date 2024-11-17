package com.ajverma.jetweatherapp.domain.util

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun DropDownMenu(
    modifier: Modifier = Modifier,
    menuIcon: ImageVector,
    menuItems: List<String>,
    onMenuItemClick: (String) -> Unit
) {
    var isDropDownMenuExpanded by remember {
        mutableStateOf(false)
    }

    Box(
        modifier = modifier
    ) {
        // Icon Button to open the Dropdown Menu
        IconButton(onClick = { isDropDownMenuExpanded = true }) {
            Icon(imageVector = menuIcon, contentDescription = "Menu")
        }

        // Dropdown Menu
        DropdownMenu(
            expanded = isDropDownMenuExpanded,
            onDismissRequest = { isDropDownMenuExpanded = false }
        ) {
            menuItems.forEach { item ->
                DropdownMenuItem(
                    text = {
                        Text(text = item)
                    },
                    onClick = {
                        isDropDownMenuExpanded = false
                        onMenuItemClick(item)
                    }
                )
            }
        }
    }
}
