package com.nurullahsevinckan.movieapp.presentation.navigation

import androidx.compose.ui.graphics.vector.ImageVector

data class BottomNavItem(
    val title : String,
    val route : String,
    val selectedIcon : ImageVector,
    val unselectedIcon : ImageVector,
    val hasNews: Boolean? = null,
    val badgeCount: Int? = null
)