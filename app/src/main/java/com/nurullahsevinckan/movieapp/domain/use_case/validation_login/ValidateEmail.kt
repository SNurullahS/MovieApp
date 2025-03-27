package com.nurullahsevinckan.movieapp.domain.use_case.validation_login

import android.util.Patterns
import javax.inject.Inject

class ValidateEmail @Inject constructor(){

    fun executeEmailValidation(email: String): ValidationResult {
        if (email.isBlank()) return ValidationResult(
            successful = false,
            errorMessage = "The email can't be blank"
        )
        if (!Patterns.EMAIL_ADDRESS.matcher(email)
                .matches()
        ) return ValidationResult(successful = false, errorMessage = "That not a valid email")
        return ValidationResult(successful = true)
    }
}