package com.nurullahsevinckan.movieapp.presentation.movie_detail.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.nurullahsevinckan.movieapp.domain.model.MovieDetail


@Composable
fun ImageDetailComp(movie: MovieDetail, isPortrait: Boolean, screenWidth: Dp) {

    Image(
        painter = rememberAsyncImagePainter(model = movie.Poster),
        contentDescription = movie.Title,
        contentScale = ContentScale.Fit, // Keep the scale
        modifier = Modifier
            .padding(16.dp)
            .then(
                if (isPortrait) {
                    Modifier.fillMaxWidth().aspectRatio(0.75f) // vertical full weight
                } else {
                    Modifier.size(screenWidth * 0.6f) // Horizontal  %60 weight
                }
            )
            .clip(RectangleShape)
    )
}


//V0.2
/*
@Composable
fun ImageDetailComp(movie: MovieDetail) {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val screenHeight = configuration.screenHeightDp.dp
    val isPortrait = screenWidth < screenHeight

    Image(
        painter = rememberAsyncImagePainter(model = movie.Poster),
        contentDescription = movie.Title,
        contentScale = ContentScale.Fit,
        modifier = Modifier
            .padding(16.dp)
            .then(
                if (isPortrait) {
                    Modifier.fillMaxWidth().aspectRatio(0.75f)
                } else {
                    Modifier.size(screenWidth * 0.6f)
                }
            )
            .clip(RectangleShape)
    )
}
 */

//V0.1
/*
@Composable
fun ImageDetailComp(movie: MovieDetail) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        Image(
            painter = rememberAsyncImagePainter(model = movie.Poster),
            contentDescription = movie.Title,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .aspectRatio(0.75f)
                .clip(RectangleShape)
        )
    }
}
 */