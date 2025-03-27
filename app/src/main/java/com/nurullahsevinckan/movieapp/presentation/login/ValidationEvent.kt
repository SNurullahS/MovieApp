package com.nurullahsevinckan.movieapp.presentation.login

sealed class ValidationEvent{
    object Successful : ValidationEvent()
}