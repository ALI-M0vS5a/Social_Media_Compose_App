package com.example.socialmedia.feature_auth.domain.use_case

import com.example.socialmedia.feature_auth.domain.repository.AuthRepository
import com.example.socialmedia.util.SimpleResource


class AuthenticateUseCase(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(): SimpleResource {
        return repository.authenticate()
    }
}