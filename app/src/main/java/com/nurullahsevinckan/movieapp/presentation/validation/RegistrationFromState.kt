package com.nurullahsevinckan.movieapp.presentation.validation

data class RegistrationFromState(
    val email : String = "",
    val emailError : String? = null,
    val password : String = "",
    val passwordError : String? = null,
    val repeatPassword : String = "",
    val repeatPasswordError : String? = null
)