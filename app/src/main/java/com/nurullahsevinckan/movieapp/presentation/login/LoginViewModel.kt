package com.nurullahsevinckan.movieapp.presentation.login

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nurullahsevinckan.movieapp.domain.use_case.firebase_auth.AuthenticationRepositoryImpl
import com.nurullahsevinckan.movieapp.util.Resource
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    private val auth : AuthenticationRepositoryImpl
):ViewModel() {

    private val _state = mutableStateOf(LoginState())
    val state : MutableState<LoginState> = _state

    //suspicious
    private fun login(email : String, password : String) {
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
            return "null"
        }
    }
}