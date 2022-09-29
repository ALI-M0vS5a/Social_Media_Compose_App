package com.example.socialmedia.feature_auth.presentation.login


sealed class LoginEvents {
    data class EnteredEmail(val email: String): LoginEvents()
    data class EnteredPassword(val password: String): LoginEvents()
    object ShowPassword: LoginEvents()
    object Login: LoginEvents()
}