package com.example.socialmedia.feature_chat.domain.use_case

import com.example.socialmedia.feature_chat.domain.model.Chat
import com.example.socialmedia.feature_chat.domain.repository.ChatRepository
import com.example.socialmedia.util.Resource


class GetChatsForUser(
    private val repository: ChatRepository
) {
    suspend operator fun invoke(): Resource<List<Chat>> {
        return repository.getChatsForUser()
    }
}