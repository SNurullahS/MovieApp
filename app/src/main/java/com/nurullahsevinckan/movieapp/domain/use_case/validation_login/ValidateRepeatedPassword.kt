package com.nurullahsevinckan.movieapp.domain.use_case.validation_login

import com.nurullahsevinckan.movieapp.util.Constants.MIN_PASSWORD_LENGTH

class ValidateRepeatedPassword {

    fun executeRepeatedPasswordValidation(password: String, repeatedPassword : String): ValidationResult {
        if(password != repeatedPassword) return  ValidationResult(successful = false, errorMessage = "Passwords do not matches!")
        return ValidationResult(successful = true)
    }
}