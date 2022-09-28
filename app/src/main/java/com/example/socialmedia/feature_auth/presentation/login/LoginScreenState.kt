package com.example.socialmedia.feature_auth.presentation.login


data class LoginScreenState(
    val usernameText: String = "",
    val passwordText: String = "",
    val showPassword: Boolean = false,
    val usernameError: String = "",
    val passwordError: String = ""
)