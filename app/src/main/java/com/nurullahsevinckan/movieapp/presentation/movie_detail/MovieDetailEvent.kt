package com.nurullahsevinckan.movieapp.presentation.movie_detail


sealed class MovieDetailEvents {
    data class AddToFavorites(val userId: String) : MovieDetailEvents()
    data class RemoveFromFavorites(val imdbId: String, val userId: String) : MovieDetailEvents()
    data class CheckFavoriteStatus(val movieId: String, val userId: String) : MovieDetailEvents()
}