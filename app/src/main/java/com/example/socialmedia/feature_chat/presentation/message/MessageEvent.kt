package com.example.socialmedia.feature_chat.presentation.message


sealed class MessageEvent {
    object SendMessage: MessageEvent()
    data class EnteredMessage(val message: String): MessageEvent()
}
