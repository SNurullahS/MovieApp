package com.nurullahsevinckan.movieapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.nurullahsevinckan.movieapp.presentation.login.LoginViewModel
import com.nurullahsevinckan.movieapp.presentation.login.views.LoginScreen
import com.nurullahsevinckan.movieapp.presentation.movie_detail.views.MovieDetailScreen
import com.nurullahsevinckan.movieapp.presentation.movies.views.MovieScreen
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

                Surface(modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background){
                    val navController  = rememberNavController()
                    val loginViewModel: LoginViewModel = hiltViewModel()
                    val isUserLoggedIn by loginViewModel.isUserLoggedIn


                    NavHost(navController = navController,
                        startDestination = if(isUserLoggedIn) Screen.MovieScreen.route
                        else Screen.LoginScreen.route){

                        //Login screen
                        composable(Screen.LoginScreen.route){
                           LoginScreen(navController)
                        }
                        //Movie screen
                        composable(route = Screen.MovieScreen.route){
                            MovieScreen(navController)
                        }

                        //Movie details screen
                        composable(route = Screen.MovieDetailScreen.route+"/{${IMDB_ID}}"){
                            MovieDetailScreen()
                        }
                    }
                }
            }
        }
    }
}
