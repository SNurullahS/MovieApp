package com.nurullahsevinckan.movieapp.presentation.movie_detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nurullahsevinckan.movieapp.domain.use_case.get_movie_detail.GetMovieDetailUseCase
import com.nurullahsevinckan.movieapp.util.Constants.IMDB_ID
import com.nurullahsevinckan.movieapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val getMovieDetailViewModel : GetMovieDetailUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf<MovieDetailState>(MovieDetailState())
    val state : State<MovieDetailState> = _state

    init {
        //composable(route = Screen.MovieDetailScreen.route+"/${IMDB_ID}") -> MainActivity
        //SaveStateHandler give us shared data in the root
        savedStateHandle.get<String>(IMDB_ID)?.let {
            getMovieDetail(it)
        }
    }

    private fun getMovieDetail(imdbId : String) {
        getMovieDetailViewModel.executeGetMovieDetail(imdbId).onEach {

            when(it){
                is Resource.Error -> {
                    _state.value = MovieDetailState(error = it.message ?: "Error!")
                }
                is Resource.Loading -> {
                    _state.value = MovieDetailState(isLoading = true)
                }
                is Resource.Success ->{
                    _state.value = MovieDetailState(movie = it.data)
                    //println("The movie title is : "+_state.value.movie?.Title)
                }
            }
        }.launchIn(viewModelScope)
    }
}