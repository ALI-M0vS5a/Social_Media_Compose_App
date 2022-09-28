package com.example.socialmedia.feature_auth.presentation.register

import com.example.socialmedia.util.UiText


data class RegisterScreenState(
    val usernameText: String = "",
    val emailText: String = "",
    val passwordText: String = "",
    val showPassword: Boolean = false,
    val usernameError: UiText? = null,
    val emailError: UiText? = null,
    val passwordError: UiText? = null,
    var isLoading: Boolean = false
)