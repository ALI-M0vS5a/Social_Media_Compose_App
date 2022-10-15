package com.example.socialmedia.feature_chat.domain.repository

import com.example.socialmedia.feature_chat.domain.model.Chat
import com.example.socialmedia.feature_chat.domain.model.Message
import com.example.socialmedia.util.Resource
import com.tinder.scarlet.WebSocket
import kotlinx.coroutines.flow.Flow


interface ChatRepository {
    fun initialize()
    suspend fun getChatsForUser(): Resource<List<Chat>>
    fun observeChatEvents(): Flow<WebSocket.Event>
    fun observeMessages(): Flow<Message>
    fun sendMessage(toId: String, text: String, chatId: String?)
    suspend fun getMessagesForChat(chatId: String, page: Int, pageSize: Int): Resource<List<Message>>
}