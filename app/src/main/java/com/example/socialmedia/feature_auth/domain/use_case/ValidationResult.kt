package com.example.socialmedia.feature_auth.domain.use_case

import com.example.socialmedia.feature_auth.UiText


data class ValidationResult(
    val successful: Boolean,
    val errorMessage: UiText.StringResource? = null
)
