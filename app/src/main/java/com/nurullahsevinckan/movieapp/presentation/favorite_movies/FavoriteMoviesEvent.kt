package com.nurullahsevinckan.movieapp.presentation.favorite_movies


sealed class FavoriteMoviesEvent {
    data class LoadFavorites(val userId: String) : FavoriteMoviesEvent()
}
