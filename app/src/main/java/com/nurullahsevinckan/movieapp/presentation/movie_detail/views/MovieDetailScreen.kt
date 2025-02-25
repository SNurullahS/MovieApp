package com.nurullahsevinckan.movieapp.presentation.movie_detail.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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

                //Movie poster
                ImageDetailComp(movie = it)

                //Movie details
                MovieDetailText(movie = it.Title)
                MovieDetailText(movie = it.Country)
                MovieDetailText(movie = it.Director)
                MovieDetailText(movie = it.imdbRating)


            }
        }

        if(state.error.isNotBlank()){
            Text(text= state.error,
                color= MaterialTheme.colorScheme.error,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(14.dp)
                    .align(Center))
        }

        if(state.isLoading){
            CircularProgressIndicator(modifier = Modifier.align(Center))
        }
    }
}