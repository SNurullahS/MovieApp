package com.nurullahsevinckan.movieapp.domain.use_case.favorite_movie

import com.nurullahsevinckan.movieapp.data.local.entity.FavoriteMovieEntity
import com.nurullahsevinckan.movieapp.domain.repository.FavoriteMovieRepository
import com.nurullahsevinckan.movieapp.util.Resource
import javax.inject.Inject

class RemoveFavoriteMovieUseCase @Inject constructor(
    private val repository: FavoriteMovieRepository
) {
    suspend operator fun invoke(movie: FavoriteMovieEntity): Resource<Unit> {
        return repository.deleteFavoriteMovie(movie)
    }
}
