package com.nurullahsevinckan.movieapp.presentation.login

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nurullahsevinckan.movieapp.domain.repository.AuthenticationRepository
import com.nurullahsevinckan.movieapp.util.Constants.USER_UID
import com.nurullahsevinckan.movieapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
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


    init {
        getUserUid()
        println(USER_UID)
    }

    private fun getUserUid(){
        viewModelScope.launch {
            auth.currentUserUid().collect{
                when (it) {
                    is Resource.Error -> {
                        _state.value = LoginState(error = it.message)
                        println("uid error")
                    }
                    is Resource.Loading -> {
                        _state.value = LoginState(isLoading = true)
                        println("uid loading!")
                    }
                    is Resource.Success -> {
                        _state.value = LoginState(userUid = it.data)
                        println("uid success")
                        val uid = it.data ?: "null!"
                        USER_UID = uid.take(5)
                    }
                }
            }
        }
    }
    private fun logIn(email: String, password: String) {
        viewModelScope.launch {
            auth.loginUser(email, password).collect {
                when (it) {
                    is Resource.Error -> {
                        _state.value = LoginState(error = it.message)
                        println("login error $email")
                    }
                    is Resource.Loading -> {
                        _state.value = LoginState(isLoading = true)
                        println("login loading $email")
                    }
                    is Resource.Success -> {
                        _state.value = LoginState(user = it.data)
                        println("login success $email")
                        USER_UID = it.data?.user?.uid ?: "Unknown UID"
                        println(USER_UID)
                    }
                }
            }
        }
    }


    private fun register(email :String,password: String){
        viewModelScope.launch {
            auth.registerUser(email,password).collect {
                when(it){
                    is Resource.Error -> {
                        _state.value = LoginState(error = it.message)
                        println("register error${email}")
                    }
                    is Resource.Loading -> {
                        _state.value = LoginState(isLoading = true)
                        println("register loading${email}")
                    }
                    is Resource.Success -> {
                        _state.value = LoginState(user = it.data)
                        println("register ssuccesfull${email}")
                        USER_UID = it.data?.user?.uid ?: "Unknown UID"
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


    fun onEvent(event : LoginEvents){
        when(event) {
            is LoginEvents.Login ->{
               logIn(event.email,event.password)
               // println(_state.value)
            }
            is LoginEvents.SignIn ->{
                //register(event.email,event.password)
               // println(_state.value)
                println(USER_UID)
            }
        }
    }

}