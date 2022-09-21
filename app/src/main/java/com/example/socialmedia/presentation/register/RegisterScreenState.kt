package com.example.socialmedia.presentation.register


data class RegisterScreenState(
    val usernameText: String = "",
    val emailText: String = "",
    val passwordText: String = "",
    val showPassword: Boolean = false,
    val usernameError: String = "",
    val emailError: String = "",
    val passwordError: String = ""
)