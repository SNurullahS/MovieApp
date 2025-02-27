package com.nurullahsevinckan.movieapp.domain.use_case.favorite_movie

import com.nurullahsevinckan.movieapp.data.local.entity.FavoriteMovieEntity
import com.nurullahsevinckan.movieapp.domain.repository.FavoriteMovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFavoriteMoviesUseCase @Inject constructor(
    private val repository: FavoriteMovieRepository
) {
    operator fun invoke(): Flow<List<FavoriteMovieEntity>> {
        return repository.getAllFavoriteMovies()
    }
}
