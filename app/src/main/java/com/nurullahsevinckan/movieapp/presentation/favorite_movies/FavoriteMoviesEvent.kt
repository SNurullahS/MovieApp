package com.nurullahsevinckan.movieapp.presentation.favorite_movies

import com.nurullahsevinckan.movieapp.data.local.entity.FavoriteMovieEntity
import com.nurullahsevinckan.movieapp.domain.model.Movie

sealed class FavoriteMoviesEvent {
    data class LoadFavorites(val userId: String) : FavoriteMoviesEvent()
    data class AddToFavorites(val movie: FavoriteMovieEntity, val userId: String) : FavoriteMoviesEvent()
    data class RemoveFromFavorites(val imdbId: String, val userId: String) : FavoriteMoviesEvent()
    data class CheckFavoriteStatus(val movieId: String, val userId: String) : FavoriteMoviesEvent()
}
