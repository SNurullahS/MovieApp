package com.nurullahsevinckan.movieapp.presentation.navigation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.outlined.Star
import com.nurullahsevinckan.movieapp.presentation.Screen

class BottomNavViewModel : ViewModel() {

    private val _bottomNavItems = MutableStateFlow(
        listOf(
            BottomNavItem(
                title = "Home",
                route = "home_screen",
                selectedIcon = Icons.Filled.Home,
                unselectedIcon = Icons.Outlined.Home
            ),
            BottomNavItem(
                title = "Movies",
                route = "movies_screen",
                selectedIcon = Icons.Filled.Star,
                unselectedIcon = Icons.Outlined.Star
            ),
            BottomNavItem(
                title = "Series",
                route = "series_screen",
                selectedIcon = Icons.Filled.MoreVert,
                unselectedIcon = Icons.Outlined.MoreVert
            )
        )
    )

    val bottomNavItems: StateFlow<List<BottomNavItem>> = _bottomNavItems
}