package com.example.socialmedia.feature_auth.presentation.login

import com.example.socialmedia.util.UiText


data class LoginScreenState(
    val emailText: String = "",
    val passwordText: String = "",
    val showPassword: Boolean = false,
    val emailError: UiText? = null,
    val passwordError: UiText? = null,
    var isLoading: Boolean = false
)