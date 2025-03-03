package com.nurullahsevinckan.movieapp.presentation.ui.composes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController

@Composable
fun OverflowMenu(navController: NavController) {
    var expanded by remember { mutableStateOf(false) }

    Box(modifier = Modifier.wrapContentSize(Alignment.TopEnd)) {
        IconButton(onClick = { expanded = true }) {
            Icon(imageVector = Icons.Default.MoreVert, contentDescription = "Menu", tint = Color.White)
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.background(Color.White)
        ) {
            DropdownMenuItem(
                text = { Text("Favorites") },
                onClick = {
                    expanded = false
                    // navController.navigate(Screen.FavoritesScreen.route)
                }
            )
            DropdownMenuItem(
                text = { Text("Sign Out") },
                onClick = {
                    expanded = false
                    // Sign-out işlemi burada çağırılabilir
                    println("User Signed Out")
                }
            )
        }
    }
}
