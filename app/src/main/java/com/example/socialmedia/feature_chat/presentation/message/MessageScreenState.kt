package com.example.socialmedia.feature_chat.presentation.message

import com.example.socialmedia.feature_chat.domain.model.Message


data class MessageScreenState(
    val messages: List<Message> = emptyList(),
    val isLoading: Boolean = false
)
