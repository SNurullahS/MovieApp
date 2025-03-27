package com.nurullahsevinckan.movieapp.domain.use_case.validation_login

data class ValidationResult(
    val successful : Boolean,
    val errorMessage: String? = null
)