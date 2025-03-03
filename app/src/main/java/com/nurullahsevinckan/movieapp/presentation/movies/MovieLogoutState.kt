package com.nurullahsevinckan.movieapp.presentation.movies

data class MovieLogoutState (
    val logout : Boolean= false,
    val logoutError : String = "",
    val logoutLoading : Boolean = false
)