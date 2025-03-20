package com.nurullahsevinckan.movieapp.data.repository

import com.nurullahsevinckan.movieapp.data.remote.MovieAPI
import com.nurullahsevinckan.movieapp.data.remote.datatransferobj.MovieDetailsDto
import com.nurullahsevinckan.movieapp.data.remote.datatransferobj.MovieDto
import com.nurullahsevinckan.movieapp.domain.repository.MovieRepository
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(private val api : MovieAPI) : MovieRepository{
    override suspend fun getMovies(search: String, page: Int, type : String?): MovieDto {
        return api.getMovies(searchString = search, page = page)
    }

    override suspend fun getMovieDetail(imdbId: String): MovieDetailsDto {
        return api.getMovieDetail(imdbId = imdbId)
    }
}