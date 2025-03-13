package com.nurullahsevinckan.movieapp.presentation.movie_detail.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.nurullahsevinckan.movieapp.domain.model.MovieDetail
import com.nurullahsevinckan.movieapp.util.ScreenUtil

@Composable
fun MovieDetailRow(movie: MovieDetail) {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        ImageDetailComp(movie = movie, isPortrait = false, screenWidth = ScreenUtil.getScreenWidth())
        MovieDetailsSection(movie)
    }
}
