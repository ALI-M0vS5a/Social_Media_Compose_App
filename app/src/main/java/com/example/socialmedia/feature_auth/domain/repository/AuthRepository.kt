package com.example.socialmedia.feature_auth.domain.repository


import com.example.socialmedia.util.SimpleResource


interface AuthRepository {

    suspend fun register(
        email: String,
        username: String,
        password: String
    ): SimpleResource

    suspend fun login(
        email: String,
        password: String
    ): SimpleResource

    suspend fun authenticate(): SimpleResource
}