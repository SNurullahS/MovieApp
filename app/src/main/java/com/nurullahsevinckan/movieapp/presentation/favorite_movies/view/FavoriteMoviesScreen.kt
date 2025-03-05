package com.nurullahsevinckan.movieapp.presentation.favorite_movies.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.nurullahsevinckan.movieapp.presentation.favorite_movies.FavoriteMoviesViewModel
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import com.google.firebase.auth.FirebaseAuth
import com.nurullahsevinckan.movieapp.presentation.Screen
import com.nurullahsevinckan.movieapp.presentation.favorite_movies.FavoriteMoviesEvent
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.text.style.TextAlign

@Composable
fun FavoriteMoviesScreen(
    navController: NavController,
    viewModel: FavoriteMoviesViewModel = hiltViewModel()
) {
    val state = viewModel.state.value

    LaunchedEffect(Unit) {
        val userId = FirebaseAuth.getInstance().currentUser?.uid ?: ""
        viewModel.onEvent(FavoriteMoviesEvent.LoadFavorites(userId))
    }

    Box(modifier = Modifier.fillMaxSize().background(Color.Black)) {
        Column {
            Text(
                text = "Favorite Movies",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.CenterHorizontally),
                color = Color.White
            )

            if (state.isLoading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
            } else if (state.favoriteMovies.isEmpty()) {
                Text(
                    text = "No favorite movies yet!",
                    textAlign = TextAlign.Center,
                    color = Color.White
                )
            } else {
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(state.favoriteMovies) { movie ->
                        FavoriteMovieListRow(movie = movie, onItemClick = {
                            navController.navigate(Screen.MovieDetailScreen.route + "/${movie.imdbID}")
                        })
                    }
                }
            }
        }
    }
}
