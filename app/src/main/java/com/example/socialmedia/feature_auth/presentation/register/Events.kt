package com.example.socialmedia.feature_auth.presentation.register


sealed class Events {
    data class EnteredEmail(val email: String): Events()
    data class EnteredUsername(val username: String): Events()
    data class EnteredPassword(val password: String): Events()
    object ShowPassword: Events()
    object Register: Events()
}
