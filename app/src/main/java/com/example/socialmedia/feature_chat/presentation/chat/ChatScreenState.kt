package com.example.socialmedia.feature_chat.presentation.chat

import com.example.socialmedia.feature_chat.domain.model.Chat


data class ChatScreenState(
    val chats: List<Chat> = emptyList(),
    val isLoading: Boolean = false
)