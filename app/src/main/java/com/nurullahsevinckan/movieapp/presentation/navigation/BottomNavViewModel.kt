package com.nurullahsevinckan.movieapp.presentation.navigation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Settings
import com.nurullahsevinckan.movieapp.presentation.Screen

class BottomNavViewModel : ViewModel() {

    private val _bottomNavItems = MutableStateFlow(
        listOf(
            BottomNavItem(
                title = "Home",
                route = Screen.MovieScreen.route,
                selectedIcon = Icons.Filled.Home,
                unselectedIcon = Icons.Outlined.Home
            ),
            BottomNavItem(
                title = "Movies",
                route = "movies_screen",
                selectedIcon = Icons.Filled.Settings,
                unselectedIcon = Icons.Outlined.Settings
            ),
            BottomNavItem(
                title = "Series",
                route = "series_screen",
                selectedIcon = Icons.Filled.Settings,
                unselectedIcon = Icons.Outlined.Settings
            )
        )
    )

    val bottomNavItems: StateFlow<List<BottomNavItem>> = _bottomNavItems
}