package com.nurullahsevinckan.movieapp.data.repository

import com.nurullahsevinckan.movieapp.data.local.dao.FavoriteMovieDao
import com.nurullahsevinckan.movieapp.data.local.entity.FavoriteMovieEntity
import com.nurullahsevinckan.movieapp.domain.repository.FavoriteMovieRepository
import com.nurullahsevinckan.movieapp.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class FavoriteMovieRepositoryImpl @Inject constructor(
    private val favoriteMovieDao: FavoriteMovieDao
) : FavoriteMovieRepository {

    override fun getAllFavoriteMovies(userId: String): Flow<Resource<List<FavoriteMovieEntity>>> = flow {
        try {
            emit(Resource.Loading())
            val movies = favoriteMovieDao.getFavoriteMoviesByUser(userId).first()
            emit(Resource.Success(movies))
        } catch (e: Exception) {
            emit(Resource.Error("Error during load favorite movie list!: ${e.message}"))
        }
    }

    override suspend fun insertFavoriteMovie(movie: FavoriteMovieEntity): Resource<Unit> {
        return try {
            favoriteMovieDao.insertFavoriteMovie(movie)
            Resource.Success(Unit)
        } catch (e: Exception) {
            Resource.Error("Error during add favorite list!: ${e.message}")
        }
    }

    override suspend fun deleteFavoriteMovie(movie: FavoriteMovieEntity): Resource<Unit> {
        return try {
            favoriteMovieDao.deleteFavoriteMovie(movie)
            Resource.Success(Unit)
        } catch (e: Exception) {
            Resource.Error("Error during deleting movie!: ${e.message}")
        }
    }

    override suspend fun isMovieFavorite(imdbID: String, userId: String): Boolean {
        return try {
            favoriteMovieDao.getFavoriteMovieById(imdbID, userId) != null
        } catch (e: Exception) {
            false
        }
    }

}
