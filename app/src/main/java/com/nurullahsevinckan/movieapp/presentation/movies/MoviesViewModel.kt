package com.nurullahsevinckan.movieapp.presentation.movies

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nurullahsevinckan.movieapp.domain.use_case.get_movie.GetMovieUseCase
import com.nurullahsevinckan.movieapp.util.Resource
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class MoviesViewModel @Inject constructor(
    private val getMovieUseCase: GetMovieUseCase
): ViewModel() {

    //Just viewModel can change the state, other classes(views) can read it
    private  val _state = mutableStateOf<MoviesState>(MoviesState())
    val state : State<MoviesState> = _state


    //If user decide to search new movie then previous jop ("Searching for movies") will be stopped
    private var  jop : Job? = null

    //When viewModel launch then default search string "Spider man" will be searched
    init{
        getMovies(_state.value.search)
    }

    private fun getMovies(search : String){
        jop?.cancel()

        jop = getMovieUseCase.executeGetMovieRepository(search).onEach {
            when(it){
                is Resource.Error -> {
                    _state.value = MoviesState(error = it.message ?: "Error!")
                }
                is Resource.Loading -> {
                    _state.value = MoviesState(isLoading = true)
                }
                is Resource.Success -> {
                    _state.value = MoviesState(movies = it.data ?: emptyList())
                }
            }

        }.launchIn(viewModelScope)
    }


    fun onEvent(event : MoviesEvent){
        when(event) {
            is MoviesEvent.Search -> {
                getMovies(event.searchString)
            }
        }
    }
}