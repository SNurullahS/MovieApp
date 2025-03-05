package com.nurullahsevinckan.movieapp.presentation.login

import android.util.Patterns
import android.widget.Toast
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nurullahsevinckan.movieapp.domain.repository.AuthenticationRepository
import com.nurullahsevinckan.movieapp.util.Constants.USER_UID
import com.nurullahsevinckan.movieapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject



@HiltViewModel
class LoginViewModel @Inject constructor(
    private val auth : AuthenticationRepository
):ViewModel() {

    private val _state = mutableStateOf(LoginState())
    val state : MutableState<LoginState> = _state

    private val _isUserLoggedIn = mutableStateOf(false)
    val isUserLoggedIn: State<Boolean> = _isUserLoggedIn

    private val _toastMessage = mutableStateOf<String?>(null)
    val toastMessage: State<String?> = _toastMessage


    init {
        getUserUid()
        checkUserLoggedIn()

    }

    private fun checkUserLoggedIn() {
        val user = auth.getCurrentUser()
        _isUserLoggedIn.value = user != null
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
                        _isUserLoggedIn.value = true
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
                        println("register successful ${email}")
                        USER_UID = it.data?.user?.uid ?: "Unknown UID"
                        _isUserLoggedIn.value = true
                    }
                }
            }
        }
    }

   //private fun signOut(){
   //    viewModelScope.launch {
   //        auth.signOut().collect {
   //            when(it){
   //                is Resource.Error -> {
   //                    _state.value = LoginState(error = it.message)
   //                }
   //                is Resource.Loading -> {
   //                    _state.value = LoginState(isLoading = true)
   //                }
   //                is Resource.Success -> {
   //                    _state.value = LoginState(user = null)
   //                    println("signOut work!")
   //                    _isUserLoggedIn.value = false
   //                }
   //            }
   //        }
   //    }
   //}

   // private fun userVerified() {
   //     viewModelScope.launch {
   //         auth.isEmailVerified().onEach {
   //             when(it){
   //                 is Resource.Error -> {
   //                     _state.value = LoginState(error = it.message)
   //                 }
   //                 is Resource.Loading -> {
   //                     _state.value = LoginState(isLoading = true)
   //                 }
   //                 is Resource.Success -> {
   //                     _state.value = LoginState(isUserVerified = it.data ?: false)
   //                 }
   //             }
   //         }
   //     }
   // }
    private fun isValidEmail(email: String): Boolean {
        return email.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun isValidPassword(password: String): Boolean {
        val passwordPattern = Regex("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#\$%^&+=!]).{8,}\$")
        return password.matches(passwordPattern)
    }

    private fun validateCredentials(email: String, password: String): Boolean {
        return isValidEmail(email) && isValidPassword(password)
    }

    fun clearToastMessage() {
        _toastMessage.value = null
    }

    fun onEvent(event : LoginEvents){
        when(event) {
            is LoginEvents.Login ->{
               logIn(event.email,event.password)
               // println(_state.value)
            }
            is LoginEvents.SignIn -> {
                if (validateCredentials(event.email, event.password)) {
                    register(event.email, event.password)
                } else {
                    _toastMessage.value = "Invalid email or password!"
                }
            }
        }
    }

}