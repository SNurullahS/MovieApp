package com.nurullahsevinckan.movieapp.presentation.movie_detail.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.nurullahsevinckan.movieapp.presentation.favorite_movies.FavoriteMoviesViewModel
import com.nurullahsevinckan.movieapp.presentation.movie_detail.MovieDetailViewModel
import com.nurullahsevinckan.movieapp.util.Constants.USER_UID
import kotlin.jvm.Throws


@Composable
fun MovieDetailScreen(
    movieDetailViewModel: MovieDetailViewModel = hiltViewModel()
) {
    val state = movieDetailViewModel.state.value
    val favoriteState = remember { mutableStateOf(false) }


  // // Film detaylarını aldıktan sonra, favori olup olmadığını kontrol et
  // LaunchedEffect(state.movie) {
  //     state.movie?.let { movie ->
  //         favoriteState.value = favoriteMovieViewModel.isMovieFavorite(movie.imdbID, USER_UID)
  //     }
  // }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
        contentAlignment = Alignment.Center
    ) {
        state.movie?.let { movie ->
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Film Posteri
                ImageDetailComp(movie = movie)

                // Film Detayları
                MovieDetailText(movie = movie.Title)
                MovieDetailText(movie = movie.Country)
                MovieDetailText(movie = movie.Director)
                MovieDetailText(movie = movie.imdbRating)
            }

               // Sağ üst köşeye favori butonu ekleme
           // IconButton(
           //     onClick = {
           //         if (favoriteState.value) {
           //             favoriteMovieViewModel.removeFavoriteMovie(movie, USER_UID)
           //         } else {
           //             favoriteMovieViewModel.addFavoriteMovie(movie, USER_UID)
           //         }
           //         favoriteState.value = !favoriteState.value
           //     },
           //     modifier = Modifier
           //         .align(Alignment.TopEnd)
           //         .padding(16.dp)
           // ) {
           //     Icon(
           //         imageVector = if (favoriteState.value) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
           //         contentDescription = "Toggle Favorite",
           //         tint = if (favoriteState.value) Color.Red else Color.White,
           //         modifier = Modifier.size(32.dp)
           //     )
           // }
        }

        if (state.error.isNotBlank()) {
            Text(
                text = state.error,
                color = MaterialTheme.colorScheme.error,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(14.dp)
                    .align(Center)
            )
        }

        if (state.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Center))
        }
    }
}
