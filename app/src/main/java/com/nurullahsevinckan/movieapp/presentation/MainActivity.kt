package com.nurullahsevinckan.movieapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.nurullahsevinckan.movieapp.presentation.login.LoginViewModel
import com.nurullahsevinckan.movieapp.presentation.login.views.LoginScreen
import com.nurullahsevinckan.movieapp.presentation.movie_detail.views.MovieDetailScreen
import com.nurullahsevinckan.movieapp.presentation.movies.views.MovieScreen
import com.nurullahsevinckan.movieapp.presentation.navigation.BottomNavViewModel
import com.nurullahsevinckan.movieapp.presentation.navigation.views.BottomNavBar
import com.nurullahsevinckan.movieapp.presentation.ui.theme.MovieAppTheme
import com.nurullahsevinckan.movieapp.util.Constants.IMDB_ID
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MovieAppTheme {
                val navController = rememberNavController()
                val loginViewModel: LoginViewModel = hiltViewModel()
                val bottomNavViewModel: BottomNavViewModel = hiltViewModel()
                val isUserLoggedIn by loginViewModel.isUserLoggedIn
                
                // Track current route to determine if bottom nav should be shown
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route
                
                // Only show bottom nav on the movie screen (not on login or detail screens)
                val showBottomNav = isUserLoggedIn && 
                                    currentRoute == Screen.MovieScreen.route

                Scaffold(
                    bottomBar = {
                        if (showBottomNav) {
                            BottomNavBar(navController, bottomNavViewModel)
                        }
                    }
                ) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = if (isUserLoggedIn) Screen.MovieScreen.route
                        else Screen.LoginScreen.route,
                        Modifier.padding(innerPadding)
                    ) {
                        // Login Screen
                        composable(Screen.LoginScreen.route) {
                            LoginScreen(navController)
                        }
                        // Movie Screen
                        composable(Screen.MovieScreen.route) {
                            MovieScreen(navController, bottomNavViewModel = bottomNavViewModel)
                        }
                        // Movie Detail Screen
                        composable(route = Screen.MovieDetailScreen.route + "/{${IMDB_ID}}") {
                            MovieDetailScreen()
                        }
                    }
                }
            }
        }
    }
}