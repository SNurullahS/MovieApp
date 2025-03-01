package com.nurullahsevinckan.movieapp.presentation.login

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nurullahsevinckan.movieapp.domain.repository.AuthenticationRepository
import com.nurullahsevinckan.movieapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val auth : AuthenticationRepository
):ViewModel() {

    private val _state = mutableStateOf(LoginState())
    val state : MutableState<LoginState> = _state

    //suspicious
    private fun logIn(email : String, password : String) {
        viewModelScope.launch {
            auth.loginUser(email,password).onEach {
                when(it){
                    is Resource.Error -> {
                        _state.value = LoginState(error = it.message)
                    }
                    is Resource.Loading -> {
                        _state.value = LoginState(isLoading = true)
                    }
                    is Resource.Success -> {
                        _state.value = LoginState(user = it.data)
                    }
                }
            }
        }

    }

    private fun register(email :String,password: String){
        viewModelScope.launch {
            auth.registerUser(email,password).onEach {
                when(it){
                    is Resource.Error -> {
                        _state.value = LoginState(error = it.message)
                    }
                    is Resource.Loading -> {
                        _state.value = LoginState(isLoading = true)
                    }
                    is Resource.Success -> {
                        _state.value = LoginState(user = it.data)
                    }
                }
            }
        }
    }

    private fun signOut(){
        viewModelScope.launch {
            auth.signOut().onEach {
                when(it){
                    is Resource.Error -> {
                        _state.value = LoginState(error = it.message)
                    }
                    is Resource.Loading -> {
                        _state.value = LoginState(isLoading = true)
                    }
                    is Resource.Success -> {
                        _state.value = LoginState(user = null)
                    }
                }
            }
        }
    }

    private fun userVerified() {
        viewModelScope.launch {
            auth.isEmailVerified().onEach {
                when(it){
                    is Resource.Error -> {
                        _state.value = LoginState(error = it.message)
                    }
                    is Resource.Loading -> {
                        _state.value = LoginState(isLoading = true)
                    }
                    is Resource.Success -> {
                        _state.value = LoginState(isUserVerified = it.data ?: false)
                    }
                }
            }
        }
    }

    private fun getCurrentUserUid() : String {
        if (_state.value.user != null){
            val userUid = auth.getCurrentUser()?.uid.toString()
            return userUid
        }else{
            return "Unknown UID"
        }
    }

    fun onEvent(event : LoginEvents){
        when(event) {
            is LoginEvents.Login ->{
                logIn(event.email,event.password)
               // println(_state.value)
            }
            is LoginEvents.SignIn ->{
                register(event.email,event.password)
               // println(_state.value)
            }
        }
    }

}