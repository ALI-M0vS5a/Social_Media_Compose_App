package com.example.socialmedia.feature_chat.data.remote.ws.data

import com.example.socialmedia.feature_chat.domain.model.Message
import java.text.DateFormat
import java.util.*


data class WsServerMessage(
    val fromId: String,
    val toId: String,
    val text: String,
    val timestamp: Long,
    val chatId: String?,
) {
    fun toMessage(): Message {
        return Message(
            fromId = fromId,
            toId = toId,
            text = text,
            FormattedTime = DateFormat
                .getDateInstance(DateFormat.DEFAULT)
                .format(Date(timestamp)),
            chatId = chatId
        )
    }
}
