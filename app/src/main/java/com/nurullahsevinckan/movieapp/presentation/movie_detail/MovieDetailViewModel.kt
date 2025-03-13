package com.nurullahsevinckan.movieapp.presentation.movie_detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nurullahsevinckan.movieapp.data.local.entity.FavoriteMovieEntity
import com.nurullahsevinckan.movieapp.domain.mapper.toFavoriteMovieEntity
import com.nurullahsevinckan.movieapp.domain.use_case.favorite_movie.AddFavoriteMovieUseCase
import com.nurullahsevinckan.movieapp.domain.use_case.favorite_movie.GetFavoriteMoviesUseCase
import com.nurullahsevinckan.movieapp.domain.use_case.favorite_movie.IsMovieFavoriteUseCase
import com.nurullahsevinckan.movieapp.domain.use_case.favorite_movie.RemoveFavoriteMovieUseCase
import com.nurullahsevinckan.movieapp.domain.use_case.get_movie_detail.GetMovieDetailUseCase
import com.nurullahsevinckan.movieapp.util.Constants.IMDB_ID
import com.nurullahsevinckan.movieapp.util.Constants.USER_UID
import com.nurullahsevinckan.movieapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val getMovieDetailUseCase: GetMovieDetailUseCase,
    private val savedStateHandle: SavedStateHandle,
  //  private val addFavoriteMovieUseCase: AddFavoriteMovieUseCase,
  //  private val removeFavoriteMovieUseCase: RemoveFavoriteMovieUseCase,
  //  private val isMovieFavoriteUseCase: IsMovieFavoriteUseCase,
) : ViewModel() {

    private val _state = mutableStateOf(MovieDetailState())
    val state: State<MovieDetailState> = _state

    init {
        savedStateHandle.get<String>(IMDB_ID)?.let {
            _state.value = MovieDetailState(movieImdb = it)
            getMovieDetail(it)
        }
    }

        private fun getMovieDetail(imdbId: String) {
            getMovieDetailUseCase.executeGetMovieDetail(imdbId).onEach { result ->
                when (result) {
                    is Resource.Error -> {
                        _state.value = MovieDetailState(error = result.message ?: "Error!")
                    }
                    is Resource.Loading -> {
                        _state.value = MovieDetailState(isLoading = true)
                    }
                    is Resource.Success -> {
                        _state.value = _state.value.copy(movie = result.data, isLoading = false)
                      //  checkIfMovieIsFavorite(imdbId, USER_UID) // Detay yüklendiğinde favori mi kontrol et

                    }
                }
            }.launchIn(viewModelScope)
        }
    /*
       private fun addMovieToFavorites(userId: String) {
           viewModelScope.launch {
               val movieDetail = _state.value.movie
               val imdbID = _state.value.movieImdb ?: ""
               if (movieDetail != null) {
                   val favoriteMovie = movieDetail.toFavoriteMovieEntity(imdbID, userId)
                   val result = addFavoriteMovieUseCase(favoriteMovie, userId)

                   if (result is Resource.Success) {
                       _state.value = _state.value.copy(isFavorite = true)
                   }
               }
           }
       }

       private fun removeMovieFromFavorites(imdbID: String, userId: String) {
           viewModelScope.launch {
               val result = removeFavoriteMovieUseCase(imdbID, userId)
               if (result is Resource.Success) {
                   _state.value = _state.value.copy(isFavorite = false)
               }
           }
       }

       private fun checkIfMovieIsFavorite(imdbID: String, userId: String) {
           viewModelScope.launch {
               val isFavorite = isMovieFavoriteUseCase(imdbID, userId)
               _state.value = _state.value.copy(isFavorite = isFavorite)
           }
       }

       fun onEvent(event: MovieDetailEvents) {
           when (event) {
               is MovieDetailEvents.AddToFavorites -> addMovieToFavorites(event.userId)
               is MovieDetailEvents.RemoveFromFavorites -> removeMovieFromFavorites(event.imdbId, event.userId)
               is MovieDetailEvents.CheckFavoriteStatus -> checkIfMovieIsFavorite(event.movieId, event.userId)
           }
       }


    */
}
