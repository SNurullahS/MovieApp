package com.nurullahsevinckan.movieapp.data.repository

import com.nurullahsevinckan.movieapp.data.local.dao.FavoriteMovieDao
import com.nurullahsevinckan.movieapp.data.local.entity.FavoriteMovieEntity
import com.nurullahsevinckan.movieapp.domain.repository.FavoriteMovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FavoriteMovieRepositoryImpl @Inject constructor(
    private val favoriteMovieDao: FavoriteMovieDao
) : FavoriteMovieRepository {

    override fun getAllFavoriteMovies(): Flow<List<FavoriteMovieEntity>> {
        return favoriteMovieDao.getAllFavoriteMovies()
    }

    override suspend fun insertFavoriteMovie(movie: FavoriteMovieEntity) {
        favoriteMovieDao.insertFavoriteMovie(movie)
    }

    override suspend fun deleteFavoriteMovie(movie: FavoriteMovieEntity) {
        favoriteMovieDao.deleteFavoriteMovie(movie)
    }

    override suspend fun isMovieFavorite(imdbID: String): Boolean {
        return favoriteMovieDao.getFavoriteMovieById(imdbID) != null
    }
}
