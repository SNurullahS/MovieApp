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
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.nurullahsevinckan.movieapp.presentation.movies.MoviesEvent
import com.nurullahsevinckan.movieapp.presentation.movies.MoviesViewModel

@Composable
fun MovieScreen(
    navController: NavController,
    viewModel : MoviesViewModel = hiltViewModel()
){
    val state = viewModel.state.value

    Box(modifier = Modifier.fillMaxSize().background(Color.Black)){

        Column(){
            MovieSearchBar(modifier = Modifier.fillMaxWidth()
                .padding(20.dp),
                hint = "Search...",
                onSearch = {
                    viewModel.onEvent(MoviesEvent.Search(it))
                })

            LazyColumn(modifier = Modifier.fillMaxSize()) {

               items(state.movies){ movie ->
                   MovieListRow(movie = movie, onItemClick = {
                    //navigation to other screen
                   })
               }
            }
        }
    }

}