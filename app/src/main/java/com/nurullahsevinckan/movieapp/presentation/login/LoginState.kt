package com.nurullahsevinckan.movieapp.presentation.login

import com.google.firebase.auth.AuthResult


data class LoginState(
    val isLoading: Boolean = false,
    val user: AuthResult? = null,
    val error: String? = null,
    val isUserVerified : Boolean? = false
)
