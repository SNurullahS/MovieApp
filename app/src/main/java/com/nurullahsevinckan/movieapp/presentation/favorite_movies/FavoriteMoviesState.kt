package com.nurullahsevinckan.movieapp.presentation.favorite_movies

import com.nurullahsevinckan.movieapp.data.local.entity.FavoriteMovieEntity

data class FavoriteMoviesState(
    val isLoading: Boolean = false,
    val favoriteMovies: List<FavoriteMovieEntity> = emptyList(),
    val error: String = ""
)
