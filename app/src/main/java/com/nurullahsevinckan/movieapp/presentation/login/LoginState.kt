package com.nurullahsevinckan.movieapp.presentation.login

import com.google.firebase.auth.AuthResult

data class LoginState (
    val isUserLoggedIn : Boolean = false,
    val error : String = "",
    val user: AuthResult,
    val isLoading : Boolean = false
)