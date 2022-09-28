package com.example.socialmedia.feature_auth.domain.use_case

import com.example.socialmedia.feature_auth.domain.repository.AuthRepository
import com.example.socialmedia.util.SimpleResource


class RegisterUseCase(
    private val repository: AuthRepository
) {

    suspend operator fun invoke(
        email: String,
        username: String,
        password: String
    ): SimpleResource {
        return repository.register(email, username, password)
    }
}