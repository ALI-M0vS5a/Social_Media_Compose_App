package com.example.socialmedia.feature_auth.domain.use_case

import android.util.Patterns
import com.example.socialmedia.R
import com.example.socialmedia.util.UiText

class ValidateEmail {
    fun execute(email: String): ValidationResult {
        if(email.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = UiText.StringResource(
                    resId = R.string.this_field_cant_be_empty
                )
            )
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return ValidationResult(
                successful = false,
                errorMessage = UiText.StringResource(
                    resId = R.string.not_a_valid_email
                )
            )
        }
        return ValidationResult(
            successful = true
        )
    }
}
