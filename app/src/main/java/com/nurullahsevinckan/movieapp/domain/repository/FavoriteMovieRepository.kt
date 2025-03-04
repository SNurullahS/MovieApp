package com.nurullahsevinckan.movieapp.domain.repository

import com.nurullahsevinckan.movieapp.data.local.entity.FavoriteMovieEntity
import com.nurullahsevinckan.movieapp.util.Resource
import kotlinx.coroutines.flow.Flow

interface FavoriteMovieRepository {
    fun getAllFavoriteMovies(userId: String): Flow<Resource<List<FavoriteMovieEntity>>>
    suspend fun insertFavoriteMovie(movie: FavoriteMovieEntity): Resource<Unit>
    suspend fun deleteFavoriteMovie(movie: FavoriteMovieEntity): Resource<Unit>
    suspend fun isMovieFavorite(imdbID: String, userId: String): Boolean
}
