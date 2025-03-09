package com.nurullahsevinckan.movieapp.presentation.movies.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.nurullahsevinckan.movieapp.presentation.Screen
import com.nurullahsevinckan.movieapp.presentation.movies.MoviesEvent
import com.nurullahsevinckan.movieapp.presentation.movies.MoviesViewModel
import com.nurullahsevinckan.movieapp.presentation.ui.composes.MovieSearchBar
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import com.nurullahsevinckan.movieapp.presentation.ui.composes.OverflowMenu

@Composable
fun MovieScreen(
    navController: NavController,
    viewModel: MoviesViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    val isLogout by viewModel.isUserLoggedOut

    LaunchedEffect(isLogout) {
        if (isLogout) {
            navController.navigate(Screen.LoginScreen.route) {
                popUpTo(Screen.MovieScreen.route) { inclusive = true }
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            // for avoid camera space
            .statusBarsPadding()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Search bar
                MovieSearchBar(
                    modifier = Modifier
                        .weight(1f),
                    hint = "Search...",
                    onSearch = {
                        viewModel.onEvent(MoviesEvent.Search(it))
                    }
                )

                //logout nad fav list
                OverflowMenu(
                    navController,
                    favList = {
                        println("Go to fav list of movies")
                    },
                    logout = {
                        println("Logged out")
                        viewModel.logoutExecute()
                    }
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                items(state.movies) { movie ->
                    MovieListRow(
                        movie = movie,
                        onItemClick = {
                            navController.navigate(
                                Screen.MovieDetailScreen.route + "/${movie.imdbID}"
                            )
                        }
                    )
                }
            }
        }
    }
}
