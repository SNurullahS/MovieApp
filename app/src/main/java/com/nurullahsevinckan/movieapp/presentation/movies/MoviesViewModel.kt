package com.nurullahsevinckan.movieapp.presentation.movies

import android.widget.Toast
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.nurullahsevinckan.movieapp.domain.repository.AuthenticationRepository
import com.nurullahsevinckan.movieapp.domain.use_case.get_movie.GetMovieUseCase
import com.nurullahsevinckan.movieapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
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

    private val _isUserLoggedOut = mutableStateOf(false)
    val isUserLoggedOut: State<Boolean> = _isUserLoggedOut

   // private val _isSearchStringEmpty = mutableStateOf(false)
   // val isSearchStringEmpty : State<Boolean> = _isSearchStringEmpty


    //If user decide to search new movie then previous jop ("Searching for movies") will be stopped
    private var job : Job? = null
    private var loadMoreJob: Job? = null

    //When viewModel launch then default search string "Spider man" will be searched
    init{
        getMovies(_state.value.search)
    }

    private fun getMovies(search : String){
        job?.cancel()
        loadMoreJob?.cancel()

        _state.value = _state.value.copy(
            isLoading = true,
            currentPage = 1,
            movies = emptyList(),
            endReached = false
        )

        job = getMovieUseCase.executeGetMovieRepository(search, 1).onEach {
            when(it){
                is Resource.Error -> {
                    _state.value = _state.value.copy(
                        isLoading = false,
                        error = it.message ?: "Error!"
                    )
                }
                is Resource.Loading -> {
                    _state.value = _state.value.copy(
                        isLoading = true
                    )
                }
                is Resource.Success -> {
                    val totalResults = it.totalResults
                    val endReached = it.data?.size ?: 0 >= totalResults || it.data?.isEmpty() == true
                    
                    _state.value = _state.value.copy(
                        isLoading = false,
                        movies = it.data ?: emptyList(),
                        totalResults = totalResults,
                        endReached = endReached,
                        search = search
                    )
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun loadMoreMovies() {
        if (_state.value.isLoading || _state.value.isLoadingMore || _state.value.endReached) {
            return
        }

        loadMoreJob?.cancel()

        val nextPage = _state.value.currentPage + 1
        
        _state.value = _state.value.copy(
            isLoadingMore = true
        )

        loadMoreJob = getMovieUseCase.loadMoreMovies(_state.value.search, nextPage).onEach { result ->
            when(result) {
                is Resource.Error -> {
                    _state.value = _state.value.copy(
                        isLoadingMore = false,
                        error = result.message ?: "Error loading more movies"
                    )
                }
                is Resource.Loading -> {
                    _state.value = _state.value.copy(
                        isLoadingMore = true
                    )
                }
                is Resource.Success -> {
                    val currentMovies = _state.value.movies
                    val newMovies = result.data ?: emptyList()
                    val totalResults = result.totalResults
                    val combinedMovies = currentMovies + newMovies
                    val endReached = combinedMovies.size >= totalResults || newMovies.isEmpty()
                    
                    _state.value = _state.value.copy(
                        isLoadingMore = false,
                        movies = combinedMovies,
                        currentPage = nextPage,
                        totalResults = totalResults,
                        endReached = endReached
                    )
                }
            }
        }.launchIn(viewModelScope)
    }

    fun logoutExecute() {
        logout()
    }

    private fun logout(){
        viewModelScope.launch {
            auth.signOut().collect {
                when(it){
                    is Resource.Error -> {
                        println(it.message)
                    }
                    is Resource.Loading -> {
                        println("Movie logout loading is worked!")
                    }
                    is Resource.Success -> {
                        _isUserLoggedOut.value = true
                        println("Movie logout work!")
                    }
                }
            }
        }
    }

    fun onEvent(event : MoviesEvent){
        when(event) {
            is MoviesEvent.Search -> {
                if(event.searchString.isNotEmpty()) {
                    getMovies(event.searchString)
                }
            }
            is MoviesEvent.LoadMore -> {
                loadMoreMovies()
            }
        }
    }


}

