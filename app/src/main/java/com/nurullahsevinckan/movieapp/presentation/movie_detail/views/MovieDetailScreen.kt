package com.nurullahsevinckan.movieapp.presentation.movie_detail.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.nurullahsevinckan.movieapp.presentation.Screen
import com.nurullahsevinckan.movieapp.presentation.movie_detail.MovieDetailState
import com.nurullahsevinckan.movieapp.presentation.movie_detail.MovieDetailViewModel

@Composable
fun MovieDetailScreen(
    movieDetailViewModel : MovieDetailViewModel = hiltViewModel()
){
    val state = movieDetailViewModel.state.value

    Box(modifier = Modifier.fillMaxSize()
        .background(Color.Black),
        contentAlignment = Alignment.Center){

        state.movie?.let {
            Column(verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally){

                ImageDetailComp(movie = it)

            }

        }
    }
}