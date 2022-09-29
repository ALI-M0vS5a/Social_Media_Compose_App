package com.example.socialmedia.feature_auth.domain.use_case

import com.example.socialmedia.feature_auth.domain.repository.AuthRepository
import com.example.socialmedia.util.SimpleResource


class LoginUseCase(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(
        email: String,
        password: String
    ): SimpleResource {
        return repository.login(email.trim(),password.trim())
    }
}