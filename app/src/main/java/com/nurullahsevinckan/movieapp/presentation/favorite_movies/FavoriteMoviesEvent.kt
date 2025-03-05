package com.nurullahsevinckan.movieapp.presentation.favorite_movies

import com.nurullahsevinckan.movieapp.data.local.entity.FavoriteMovieEntity
import com.nurullahsevinckan.movieapp.domain.model.Movie

sealed class FavoriteMoviesEvent {
    data class LoadFavorites(val userId: String) : FavoriteMoviesEvent()
}
