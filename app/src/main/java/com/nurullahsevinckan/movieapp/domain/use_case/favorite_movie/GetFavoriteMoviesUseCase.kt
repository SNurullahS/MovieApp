package com.nurullahsevinckan.movieapp.domain.use_case.favorite_movie

import com.nurullahsevinckan.movieapp.data.local.entity.FavoriteMovieEntity
import com.nurullahsevinckan.movieapp.domain.repository.FavoriteMovieRepository
import com.nurullahsevinckan.movieapp.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFavoriteMoviesUseCase @Inject constructor(
    private val repository: FavoriteMovieRepository
) {
    operator fun invoke(userId: String): Flow<Resource<List<FavoriteMovieEntity>>> {
        return repository.getAllFavoriteMovies(userId)
    }
}
