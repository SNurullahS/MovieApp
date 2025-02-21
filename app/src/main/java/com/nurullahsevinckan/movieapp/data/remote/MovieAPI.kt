package com.nurullahsevinckan.movieapp.data.remote

import com.nurullahsevinckan.movieapp.data.remote.datatransferobj.MovieDetailsDto
import com.nurullahsevinckan.movieapp.data.remote.datatransferobj.MovieDto
import com.nurullahsevinckan.movieapp.util.Constants.API_KEY
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieAPI {

    // http://www.omdbapi.com/?i=tt3896198&apikey=557e8fca -> "i" is imdb id
    // http://www.omdbapi.com/?s=batman&apikey=557e8fca -> "s" is search string

    @GET(".")
    suspend fun getMovies(
        @Query("s") searchString: String,
        @Query("apikey") apiKey :String = API_KEY
    ) : MovieDto

    @GET(".")
    suspend fun getMovieDetail(
        @Query("i") imdbId : String,
        @Query("apikey") apiKey : String = API_KEY
    ) : MovieDetailsDto
}