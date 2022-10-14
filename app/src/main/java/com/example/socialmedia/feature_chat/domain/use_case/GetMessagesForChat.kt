package com.example.socialmedia.feature_chat.domain.use_case

import com.example.socialmedia.feature_chat.domain.model.Message
import com.example.socialmedia.feature_chat.domain.repository.ChatRepository
import com.example.socialmedia.util.Constants
import com.example.socialmedia.util.Resource

class GetMessagesForChat(
    private val repository: ChatRepository
) {
    suspend operator fun invoke(
        chatId: String,
        page: Int,
        pageSize: Int = Constants.DEFAULT_PAGE_SIZE
    ): Resource<List<Message>> {
        return repository.getMessagesForChat(chatId, page, pageSize)
    }
}