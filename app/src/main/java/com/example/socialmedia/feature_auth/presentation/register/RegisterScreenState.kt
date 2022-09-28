package com.example.socialmedia.feature_auth.presentation.register

import com.example.socialmedia.feature_auth.UiText


data class RegisterScreenState(
    val usernameText: String = "",
    val emailText: String = "",
    val passwordText: String = "",
    val showPassword: Boolean = false,
    val usernameError: UiText? = null,
    val emailError: UiText? = null,
    val passwordError: UiText? = null
) {
    sealed class UsernameError {
        object FieldEmpty: UsernameError()
        object InputTooShort: UsernameError()
    }
    sealed class EmailError {
        object FieldEmpty: EmailError()
        object InvalidEmail: EmailError()
    }
    sealed class PasswordError {
        object FieldEmpty: PasswordError()
        object InvalidPassword: PasswordError()
        object InputTooShort: PasswordError()
    }

}