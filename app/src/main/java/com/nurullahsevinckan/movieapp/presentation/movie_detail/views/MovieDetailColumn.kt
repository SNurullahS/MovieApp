package com.nurullahsevinckan.movieapp.presentation.movie_detail.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.nurullahsevinckan.movieapp.domain.model.MovieDetail
import com.nurullahsevinckan.movieapp.util.ScreenUtil


@Composable
fun MovieDetailColumn(movie: MovieDetail) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        ImageDetailComp(movie = movie, isPortrait = true, screenWidth = ScreenUtil.getScreenWidth())
        MovieDetailsSection(movie)
    }
}
