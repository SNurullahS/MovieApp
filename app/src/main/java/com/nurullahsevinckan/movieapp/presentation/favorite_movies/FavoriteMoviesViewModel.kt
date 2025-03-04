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
    private val getFavoriteMoviesUseCase: GetFavoriteMoviesUseCase,
    private val addFavoriteMovieUseCase: AddFavoriteMovieUseCase,
    private val removeFavoriteMovieUseCase: RemoveFavoriteMovieUseCase,
    private val isMovieFavoriteUseCase: IsMovieFavoriteUseCase,
    private val auth: AuthenticationRepository
) : ViewModel() {

    private val _state = mutableStateOf(FavoriteMoviesState())
    val state: State<FavoriteMoviesState> = _state

    private val _isUserLoggedOut = mutableStateOf(false)
    val isUserLoggedOut: State<Boolean> = _isUserLoggedOut

    init {
            onEvent(FavoriteMoviesEvent.LoadFavorites(USER_UID))

    }

    fun onEvent(event: FavoriteMoviesEvent) {
        when (event) {
            is FavoriteMoviesEvent.LoadFavorites -> {
                loadFavoriteMovies(event.userId)
            }
            is FavoriteMoviesEvent.AddToFavorites -> {
                addMovieToFavorites(event.movie, event.userId)
            }
            is FavoriteMoviesEvent.RemoveFromFavorites -> {
                removeMovieFromFavorites(event.imdbId, event.userId)
            }
            is FavoriteMoviesEvent.CheckFavoriteStatus -> {
                checkIfMovieIsFavorite(event.movieId, event.userId)
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
                        _state.value = FavoriteMoviesState(error = result.message ?: "Favori filmler yüklenirken hata oluştu!")
                    }
                }
            }
        }

    }

    private fun addMovieToFavorites(movie: FavoriteMovieEntity, userId: String) {
        viewModelScope.launch {
            val favoriteMovie = FavoriteMovieEntity(
                imdbID = movie.imdbID,
                title = movie.title,
                year = movie.year,
                poster = movie.poster,
                imdbRating = "",
                userId = userId
            )
            val result = addFavoriteMovieUseCase(favoriteMovie, userId)

            if (result is Resource.Success) {
                onEvent(FavoriteMoviesEvent.LoadFavorites(userId))
            }
        }
    }

    private fun removeMovieFromFavorites(imdbID: String, userId: String) {
        viewModelScope.launch {
            val result = removeFavoriteMovieUseCase(imdbID,userId)

            if (result is Resource.Success) {
                onEvent(FavoriteMoviesEvent.LoadFavorites(userId)) // Favori listesini güncelle
            }
        }
    }

    private fun checkIfMovieIsFavorite(imdbID: String, userId: String) {
        viewModelScope.launch {
            val isFavorite = isMovieFavoriteUseCase(imdbID, userId)
            _state.value = _state.value.copy(isFavorite = isFavorite)
        }
    }

    fun logoutExecute() {
        viewModelScope.launch {
            auth.signOut().collect {
                when (it) {
                    is Resource.Error -> {
                        println(it.message)
                    }
                    is Resource.Loading -> {
                        println("Favori filmler logout işlemi devam ediyor!")
                    }
                    is Resource.Success -> {
                        _isUserLoggedOut.value = true
                        println("Favori filmler logout işlemi tamamlandı!")
                    }
                }
            }
        }
    }
}
