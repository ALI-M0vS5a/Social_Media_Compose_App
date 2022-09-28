package com.example.socialmedia.feature_auth.domain.use_case

import com.example.socialmedia.R
import com.example.socialmedia.util.UiText
import com.example.socialmedia.util.Constants


class ValidatePassword {
    fun execute(password: String): ValidationResult {
        if (password.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = UiText.StringResource(
                    resId = R.string.this_field_cant_be_empty
                )
            )
        }
        if (password.length < Constants.MIN_PASSWORD_LENGTH) {
            return ValidationResult(
                successful = false,
                errorMessage = UiText.StringResource(
                    resId = R.string.imput_too_short,
                    3
                )
            )
        }
        val capitalLettersInPassword = password.any { it.isUpperCase() }
        val numberInPassword = password.any { it.isDigit() }
        if (!capitalLettersInPassword || !numberInPassword) {
            return ValidationResult(
                successful = false,
                errorMessage = UiText.StringResource(
                    resId = R.string.invalid_password
                )
            )
        }
        return ValidationResult(
            successful = true
        )
    }
}
