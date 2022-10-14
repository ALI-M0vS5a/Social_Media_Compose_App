package com.example.socialmedia.feature_chat.data.remote.data

import com.example.socialmedia.feature_chat.domain.model.Message


data class MessageDto(
    val fromId: String,
    val toId: String,
    val text: String,
    val timestamp: String,
    val chatId: String?,
    val id: String
) {
    fun toMessage(): Message {
        return Message(
            fromId = fromId,
            toId = toId,
            text = text,
            formattedTime = timestamp,
            chatId = chatId
        )
    }
}
