package com.nurullahsevinckan.movieapp.presentation.movie_detail.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.nurullahsevinckan.movieapp.domain.model.MovieDetail
import com.nurullahsevinckan.movieapp.presentation.ui.composes.MovieDetailText

@Composable
fun MovieDetailsSection(movie: MovieDetail) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(16.dp)
    ) {
        MovieDetailText(movie.Title)
        MovieDetailText(movie.Country)
        MovieDetailText(movie.Director)
        MovieDetailText(movie.imdbRating)
    }
}
