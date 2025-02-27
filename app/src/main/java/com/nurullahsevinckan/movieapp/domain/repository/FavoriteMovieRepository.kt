package com.nurullahsevinckan.movieapp.domain.repository

import com.nurullahsevinckan.movieapp.data.local.entity.FavoriteMovieEntity
import kotlinx.coroutines.flow.Flow

interface FavoriteMovieRepository {
    fun getAllFavoriteMovies(): Flow<List<FavoriteMovieEntity>>
    suspend fun insertFavoriteMovie(movie: FavoriteMovieEntity)
    suspend fun deleteFavoriteMovie(movie: FavoriteMovieEntity)
    suspend fun isMovieFavorite(imdbID: String): Boolean
}
