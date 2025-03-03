package com.nurullahsevinckan.movieapp.presentation.movies

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nurullahsevinckan.movieapp.domain.repository.AuthenticationRepository
import com.nurullahsevinckan.movieapp.domain.use_case.get_movie.GetMovieUseCase
import com.nurullahsevinckan.movieapp.presentation.login.LoginState
import com.nurullahsevinckan.movieapp.util.Constants.USER_UID
import com.nurullahsevinckan.movieapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val getMovieUseCase: GetMovieUseCase,
    private val auth : AuthenticationRepository
): ViewModel() {

    //Just viewModel can change the state, other classes(views) can read it
    private  val _state = mutableStateOf<MoviesState>(MoviesState())
    val state : State<MoviesState> = _state

    private val _logoutState = mutableStateOf(MovieLogoutState())
    val logoutState : State<MovieLogoutState> = _logoutState

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

    private fun signOut(){
        viewModelScope.launch {
            auth.signOut().collect {
                when(it){
                    is Resource.Error -> {
                        _logoutState.value = MovieLogoutState(logoutError = it.message ?: "Logout error!")
                    }
                    is Resource.Loading -> {
                        _logoutState.value = MovieLogoutState(logoutLoading = true)
                    }
                    is Resource.Success -> {
                        _logoutState.value = MovieLogoutState(logout = true)
                        println("Movie signOut work!")
                    }
                }
            }
        }
    }


    fun onEvent(event : MoviesEvent){
        when(event) {
            is MoviesEvent.Search -> {
                getMovies(event.searchString)
            }
        }
    }
}