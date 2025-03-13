package com.nurullahsevinckan.movieapp.presentation.movie_detail.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.nurullahsevinckan.movieapp.presentation.movie_detail.MovieDetailViewModel
import com.nurullahsevinckan.movieapp.presentation.ui.composes.FavoriteIconButton
import com.nurullahsevinckan.movieapp.util.ScreenUtil
import kotlin.jvm.Throws


@Composable
fun MovieDetailScreen(
    movieDetailViewModel: MovieDetailViewModel = hiltViewModel()
) {
    val state = movieDetailViewModel.state.value
    val isPortrait = ScreenUtil.isPortrait()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .statusBarsPadding()
    ) {
        state.movie?.let { movie ->
            if (isPortrait) MovieDetailColumn(movie) else MovieDetailRow(movie)
        }

        FavoriteIconButton(
            isFavorite = state.isFavorite,
            onClick = { println("Onclick") },
            modifier = Modifier.align(Alignment.TopEnd)
        )

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


/*
LaunchedEffect(state.movieImdb) {
  state.movie?.let { movie ->
      movieDetailViewModel.onEvent(
          MovieDetailEvents.CheckFavoriteStatus(state.movieImdb, USER_UID)
      )
  }

   IconButton(
                onClick = {
                    println("Onclick ")
                  // if (state.isFavorite) {
                  //     movieDetailViewModel.onEvent(
                  //         MovieDetailEvents.RemoveFromFavorites(IMDB_ID, USER_UID)
                  //     )
                  // } else {
                  //     movieDetailViewModel.onEvent(
                  //         MovieDetailEvents.AddToFavorites(USER_UID)
                  //     )
                  // }
                },
}*/