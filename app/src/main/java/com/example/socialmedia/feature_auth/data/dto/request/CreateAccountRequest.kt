package com.example.socialmedia.feature_auth.data.dto.request


data class CreateAccountRequest(
    val email: String,
    val username: String,
    val password: String
)