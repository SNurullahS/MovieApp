package com.nurullahsevinckan.movieapp.domain.use_case.validation_login

import android.util.Patterns
import com.nurullahsevinckan.movieapp.util.Constants.MIN_PASSWORD_LENGTH

class ValidatePassword {

    fun executePasswordValidation(password: String): ValidationResult {
        if (password.length < MIN_PASSWORD_LENGTH) return ValidationResult(
            successful = false,
            errorMessage = "The password needs to consist at  least $MIN_PASSWORD_LENGTH characters"
        )
        val isPasswordContainsRequirements = with(password) {
            any { it.isDigit() } &&
                    any { it.isLowerCase() } &&
                    any { it.isUpperCase() } &&
                    any { it.isLetter() }
        }
        if (!isPasswordContainsRequirements) return ValidationResult(
            successful = false,
            errorMessage = "The password needs to contain at least one letter, uppercase, lowercase, number and special character"
        )
        return ValidationResult(successful = true)
    }
}