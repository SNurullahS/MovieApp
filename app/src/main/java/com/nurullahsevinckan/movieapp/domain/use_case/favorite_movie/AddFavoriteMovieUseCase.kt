package com.nurullahsevinckan.movieapp.domain.use_case.favorite_movie

import com.nurullahsevinckan.movieapp.data.local.entity.FavoriteMovieEntity
import com.nurullahsevinckan.movieapp.domain.repository.FavoriteMovieRepository
import com.nurullahsevinckan.movieapp.util.Resource
import javax.inject.Inject

class AddFavoriteMovieUseCase @Inject constructor(
    private val repository: FavoriteMovieRepository
) {
    //User id is already in move. Can be deleted
    suspend operator fun invoke(movie: FavoriteMovieEntity, userId: String): Resource<Unit> {
        return if (repository.isMovieFavorite(movie.imdbID, userId)) {
            Resource.Error("That movie is already in favorite list!")
        } else {
            repository.insertFavoriteMovie(movie)
        }
    }
}
