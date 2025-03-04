package com.nurullahsevinckan.movieapp.data.local.dao

import androidx.room.*
import com.nurullahsevinckan.movieapp.data.local.entity.FavoriteMovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteMovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavoriteMovie(movie: FavoriteMovieEntity)

    @Query("DELETE FROM favorite_movies WHERE imdbID = :imdbID AND userId = :userId")
    suspend fun deleteFavoriteMovie(imdbID: String, userId: String)

    @Query("SELECT * FROM favorite_movies WHERE userId = :userId")
    fun getFavoriteMoviesByUser(userId: String): Flow<List<FavoriteMovieEntity>>

    @Query("SELECT * FROM favorite_movies WHERE imdbID = :imdbID AND userId = :userId")
    suspend fun getFavoriteMovieById(imdbID: String, userId: String): FavoriteMovieEntity?
}
