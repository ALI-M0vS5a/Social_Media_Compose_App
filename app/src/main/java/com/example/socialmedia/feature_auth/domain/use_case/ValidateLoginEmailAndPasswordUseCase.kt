package com.example.socialmedia.feature_auth.domain.use_case

import com.example.socialmedia.R
import com.example.socialmedia.util.UiText


class ValidateLoginEmailAndPasswordUseCase {
    fun execute(email: String, password: String): ValidationResult {
        if(email.isBlank() || password.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = UiText.StringResource(
                    resId = R.string.this_field_cant_be_empty
                )
            )
        }
        return ValidationResult(
            successful = true
        )
    }
}