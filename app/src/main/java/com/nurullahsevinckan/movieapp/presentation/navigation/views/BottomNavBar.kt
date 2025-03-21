package com.nurullahsevinckan.movieapp.presentation.navigation.views

import android.util.Log
import androidx.compose.foundation.layout.size
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.hilt.navigation.compose.hiltViewModel
import com.nurullahsevinckan.movieapp.presentation.navigation.BottomNavViewModel

@Composable
fun BottomNavBar(
    navController: NavController,
    viewModel: BottomNavViewModel
) {
    val items by viewModel.bottomNavItems.collectAsState()
    val selectedItemIndex by viewModel.selectedItemIndex.collectAsState()

    // Set initial selected item
    LaunchedEffect(Unit) {
        Log.d("BottomNavBar", "Initial setup - setting index to 0")
        viewModel.setSelectedIndex(0)
    }

    NavigationBar(
        containerColor = Color.Black
    ) {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = selectedItemIndex == index,
                onClick = {
                    Log.d("BottomNavBar", "Item clicked: ${item.title} (index: $index)")
                    viewModel.setSelectedIndex(index)
                },
                label = {
                    Text(
                        item.title,
                        color = Color.White,
                        style = MaterialTheme.typography.bodyMedium,
                    )
                },
                icon = {
                    BadgedBox(
                        badge = {
                            if (item.badgeCount != null) {
                                Badge {
                                    Text(text = item.badgeCount.toString())
                                }
                            } else if (item.hasNews == true) {
                                Badge()
                            }

                        }
                    ) {
                        Icon(
                            imageVector =
                            if (index == selectedItemIndex) {
                                item.selectedIcon
                            } else item.unselectedIcon,
                            contentDescription = item.title,
                            tint = Color.LightGray,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }
            )
        }
    }
}