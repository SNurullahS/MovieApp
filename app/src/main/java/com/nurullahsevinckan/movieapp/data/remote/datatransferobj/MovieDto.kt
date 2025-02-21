package com.nurullahsevinckan.movieapp.data.remote.datatransferobj

import com.nurullahsevinckan.movieapp.domain.model.Movie

data class MovieDto(
    val Response: String,
    val Search: List<Search>,
    val totalResults: String
)

//To send usable data to domain layer
fun MovieDto.toMovieList() : List<Movie>{
    return Search.map { search -> Movie(search.Poster,search.Title,search.Year,search.imdbID) }
}