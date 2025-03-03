package com.nurullahsevinckan.movieapp.presentation.movies

import com.nurullahsevinckan.movieapp.data.remote.datatransferobj.Search
import com.nurullahsevinckan.movieapp.domain.model.Movie

data class MoviesState (
    val isLoading : Boolean  = false,
    val movies : List<Movie> = emptyList(),
    val error : String = "",
    val search: String = "Spider man"

)