package com.nurullahsevinckan.movieapp.domain.repository

import com.nurullahsevinckan.movieapp.data.remote.datatransferobj.MovieDetailsDto
import com.nurullahsevinckan.movieapp.data.remote.datatransferobj.MovieDto

interface MovieRepository {
    suspend fun getMovies(search: String) : MovieDto
    suspend fun getMovieDetail(imdbId : String) : MovieDetailsDto
}