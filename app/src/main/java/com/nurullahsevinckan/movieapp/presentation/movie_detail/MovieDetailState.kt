package com.nurullahsevinckan.movieapp.presentation.movie_detail

import com.nurullahsevinckan.movieapp.domain.model.MovieDetail
import com.nurullahsevinckan.movieapp.util.Resource

data class MovieDetailState(
    val movieImdb : String ="",
    val isLoading: Boolean = false,
    val movie : MovieDetail? = null,
    val error : String = "",
    val isFavorite: Boolean = false
)