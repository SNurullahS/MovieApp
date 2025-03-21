package com.nurullahsevinckan.movieapp.presentation.navigation

import android.util.Log
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
    
    // Add content type tracking
    private val _selectedContentType = MutableStateFlow<String?>(null)
    val selectedContentType: StateFlow<String?> = _selectedContentType
    
    // Add search hint tracking
    private val _searchHint = MutableStateFlow("Search...")
    val searchHint: StateFlow<String> = _searchHint
    
    // Track the selected index
    private val _selectedItemIndex = MutableStateFlow(0)
    val selectedItemIndex: StateFlow<Int> = _selectedItemIndex
    
    fun setSelectedIndex(index: Int) {
        Log.d("BottomNavViewModel", "Setting selected index to: $index")
        _selectedItemIndex.value = index
        
        // Update content type based on selection
        when(index) {
            0 -> { // Home
                Log.d("BottomNavViewModel", "Setting type to null (Home)")
                _selectedContentType.value = null
                _searchHint.value = "Search for movies & series..."
            }
            1 -> { // Movies
                Log.d("BottomNavViewModel", "Setting type to movie")
                _selectedContentType.value = "movie"
                _searchHint.value = "Search for movies..."
            }
            2 -> { // Series
                Log.d("BottomNavViewModel", "Setting type to series")
                _selectedContentType.value = "series"
                _searchHint.value = "Search for series..."
            }
        }
        Log.d("BottomNavViewModel", "New search hint: ${_searchHint.value}")
        Log.d("BottomNavViewModel", "New content type: ${_selectedContentType.value}")
    }
}