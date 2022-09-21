package com.example.socialmedia.presentation.login


data class LoginScreenState(
    val usernameText: String = "",
    val passwordText: String = "",
    val showPassword: Boolean = false,
    val usernameError: String = "",
    val passwordError: String = ""
)