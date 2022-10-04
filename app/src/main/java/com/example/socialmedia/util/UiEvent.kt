package com.example.socialmedia.util


sealed class UiEvent {
    data class Message(val uiText: UiText): UiEvent()
    data class Navigate(val route: String): UiEvent()
    data class Success(val uiText: UiText): UiEvent()
    object NavigateUp: UiEvent()
}
