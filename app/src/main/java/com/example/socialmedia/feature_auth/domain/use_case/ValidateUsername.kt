package com.example.socialmedia.feature_auth.domain.use_case

import com.example.socialmedia.R
import com.example.socialmedia.util.UiText
import com.example.socialmedia.util.Constants


class ValidateUsername {
    fun execute(username: String): ValidationResult {
        if (username.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = UiText.StringResource(
                    resId = R.string.this_field_cant_be_empty
                )
            )
        }
        if (username.length < Constants.MIN_USERNAME_LENGTH) {
            return ValidationResult(
                successful = false,
                errorMessage = UiText.StringResource(
                    resId = R.string.imput_too_short,
                    3
                )
            )
        }
        return ValidationResult(
            successful = true
        )
    }
}

