package com.nurullahsevinckan.movieapp.presentation.movie_detail.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.nurullahsevinckan.movieapp.domain.model.MovieDetail
@Composable
fun ImageDetailComp(movie: MovieDetail) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        Image(
            painter = rememberAsyncImagePainter(model = movie.Poster),
            contentDescription = movie.Title,
            contentScale = ContentScale.Crop, // Resmin alanı doldururken oranı korur
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()   // Ekranın genişliğini doldurur
                .aspectRatio(0.75f)  // Yükseklik, genişliğin 1 katı olur
                .clip(RectangleShape)
        )
    }
}
