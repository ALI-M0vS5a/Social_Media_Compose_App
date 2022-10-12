package com.example.socialmedia.feature_chat.domain.model


data class Message(
    val fromId: String,
    val toId: String,
    val text: String,
    val FormattedTime: String,
    val chatId: String,
    val id: String
)
