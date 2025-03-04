package com.nurullahsevinckan.movieapp.domain.use_case.favorite_movie

import com.nurullahsevinckan.movieapp.domain.repository.FavoriteMovieRepository
import javax.inject.Inject

class IsMovieFavoriteUseCase @Inject constructor(
    private val repository: FavoriteMovieRepository
) {
    suspend operator fun invoke(imdbID: String, userId: String): Boolean {
        return repository.isMovieFavorite(imdbID, userId)
    }
}
