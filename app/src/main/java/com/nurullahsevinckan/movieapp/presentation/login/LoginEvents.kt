package com.nurullahsevinckan.movieapp.presentation.login

sealed class LoginEvents {
    data class Login(val email : String, val password : String) : LoginEvents()
    data class EmailChecked(val email : String) : LoginEvents()
    data class PasswordChanged(val password: String) : LoginEvents()
    data class RepeatedPasswordChanged(val repeatedPassword : String) : LoginEvents()
    data class SignIn(val email : String , val password: String) : LoginEvents()
}