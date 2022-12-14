package com.example.socialmedia.feature_chat.data.repository

import com.example.socialmedia.R
import com.example.socialmedia.feature_chat.data.remote.ChatApi
import com.example.socialmedia.feature_chat.data.remote.ChatService
import com.example.socialmedia.feature_chat.data.remote.data.WsClientMessage
import com.example.socialmedia.feature_chat.domain.di.ScarletInstance
import com.example.socialmedia.feature_chat.domain.model.Chat
import com.example.socialmedia.feature_chat.domain.model.Message
import com.example.socialmedia.feature_chat.domain.repository.ChatRepository
import com.example.socialmedia.util.Resource
import com.example.socialmedia.util.UiText
import com.tinder.scarlet.WebSocket
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.receiveAsFlow
import okhttp3.OkHttpClient
import retrofit2.HttpException
import java.io.IOException


class ChatRepositoryImpl(
    private val chatApi: ChatApi,
    private val client: OkHttpClient
) : ChatRepository {

    private var chatService: ChatService ?= null

    override fun initialize() {
        chatService = ScarletInstance.getNewInstance(client)
    }

    override suspend fun getChatsForUser(): Resource<List<Chat>> {
        return try {
            val chats = chatApi.getChatsForUser().mapNotNull { it.toChat() }
            Resource.Success(data = chats)
        }  catch (e: IOException) {
            Resource.Error(
                message = UiText.StringResource(
                    resId = R.string.please_check_your_connection
                )
            )
        } catch (e: HttpException) {
            Resource.Error(
                message = UiText.StringResource(
                    resId = R.string.Oops_something_went_wrong
                )
            )
        }
    }

    override  fun observeChatEvents(): Flow<WebSocket.Event> {
       return chatService
           ?.observeEvents()
           ?.receiveAsFlow() ?: flow {  }
    }

    override  fun observeMessages(): Flow<Message> {
        return chatService
            ?.observeMessages()
            ?.receiveAsFlow()
            ?.map { it.toMessage() } ?: flow {  }
    }

    override  fun sendMessage(toId: String, text: String, chatId: String?) {
        chatService?.sendMessage(
            WsClientMessage(
                toId = toId,
                text = text,
                chatId = chatId
            )
        )
    }

    override suspend fun getMessagesForChat(
        chatId: String,
        page: Int,
        pageSize: Int
    ): Resource<List<Message>> {
        return try {
            val messages = chatApi
                .getMessagesForChat(chatId, page, pageSize)
                .map { it.toMessage() }
            Resource.Success(data = messages)
        }  catch (e: IOException) {
            Resource.Error(
                message = UiText.StringResource(
                    resId = R.string.please_check_your_connection
                )
            )
        } catch (e: HttpException) {
            Resource.Error(
                message = UiText.StringResource(
                    resId = R.string.Oops_something_went_wrong
                )
            )
        }
    }
}