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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
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
                //if(user.oauth){status = true}

                Surface(modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background){

                    val navController  = rememberNavController()
                    NavHost(navController = navController, startDestination = Screen.LoginScreen.route){

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
