package com.nurullahsevinckan.movieapp.presentation.favorite_movies

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.nurullahsevinckan.movieapp.domain.repository.AuthenticationRepository
import com.nurullahsevinckan.movieapp.domain.use_case.favorite_movie.AddFavoriteMovieUseCase
import com.nurullahsevinckan.movieapp.domain.use_case.favorite_movie.GetFavoriteMoviesUseCase
import com.nurullahsevinckan.movieapp.domain.use_case.favorite_movie.IsMovieFavoriteUseCase
import com.nurullahsevinckan.movieapp.domain.use_case.favorite_movie.RemoveFavoriteMovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import androidx.compose.runtime.State
import androidx.lifecycle.viewModelScope
import com.nurullahsevinckan.movieapp.data.local.entity.FavoriteMovieEntity
import com.nurullahsevinckan.movieapp.util.Constants.USER_UID
import com.nurullahsevinckan.movieapp.util.Resource
import kotlinx.coroutines.launch

@HiltViewModel
class FavoriteMoviesViewModel @Inject constructor(
    private val getFavoriteMoviesUseCase: GetFavoriteMoviesUseCase
) : ViewModel() {

    private val _state = mutableStateOf(FavoriteMoviesState())
    val state: State<FavoriteMoviesState> = _state

    fun onEvent(event: FavoriteMoviesEvent) {
        when (event) {
            is FavoriteMoviesEvent.LoadFavorites -> {
                loadFavoriteMovies(event.userId)
            }
        }
    }

    private fun loadFavoriteMovies(userId: String) {
        viewModelScope.launch {

            getFavoriteMoviesUseCase(userId).collect() { result ->
                when (result) {
                    is Resource.Loading -> {
                        _state.value = FavoriteMoviesState(isLoading = true)
                    }
                    is Resource.Success -> {
                        _state.value = FavoriteMoviesState(favoriteMovies = result.data ?: emptyList())
                    }
                    is Resource.Error -> {
                        _state.value = FavoriteMoviesState(error = result.message ?: "Can not loading favorite movies!")
                    }
                }
            }
        }

    }

}


