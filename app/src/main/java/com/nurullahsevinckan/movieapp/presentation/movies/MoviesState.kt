package com.nurullahsevinckan.movieapp.presentation.movies

import com.nurullahsevinckan.movieapp.domain.model.Movie

data class MoviesState (
    val isLoading : Boolean  = false,
    val isLoadingMore: Boolean = false,
    val movies : List<Movie> = emptyList(),
    val error : String = "",
    val search: String = "Spider man",
    val currentPage: Int = 1,
    val totalResults: Int = 0,
    val endReached: Boolean = false
)