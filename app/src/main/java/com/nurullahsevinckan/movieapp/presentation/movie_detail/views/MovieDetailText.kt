package com.nurullahsevinckan.movieapp.presentation.movie_detail.views

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nurullahsevinckan.movieapp.domain.model.MovieDetail


@Composable
fun MovieDetailText(
    movie : String
){
    Text(text = movie,
        textAlign = TextAlign.Center,
        modifier = Modifier.padding(14.dp),
        color = Color.White,
        fontSize = 15.sp)
}