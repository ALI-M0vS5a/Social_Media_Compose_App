package com.example.socialmedia.feature_chat.domain.di

import android.app.Application
import com.example.socialmedia.feature_auth.data.remote.AuthApi
import com.example.socialmedia.feature_chat.data.remote.ChatApi
import com.example.socialmedia.feature_chat.data.remote.ChatService
import com.example.socialmedia.feature_chat.data.remote.util.CustomGsonMessageAdapter
import com.example.socialmedia.feature_chat.data.remote.util.FlowStreamAdapter
import com.example.socialmedia.feature_chat.data.repository.ChatRepositoryImpl
import com.example.socialmedia.feature_chat.domain.repository.ChatRepository
import com.example.socialmedia.feature_chat.domain.use_case.*
import com.example.socialmedia.util.BaseUrl
import com.example.socialmedia.util.BaseUrl.Companion.BASE_URL
import com.example.socialmedia.util.Constants
import com.google.gson.Gson
import com.tinder.scarlet.Scarlet
import com.tinder.scarlet.lifecycle.android.AndroidLifecycle
import com.tinder.scarlet.retry.LinearBackoffStrategy
import com.tinder.scarlet.websocket.okhttp.newWebSocketFactory
import com.tinder.streamadapter.coroutines.CoroutinesStreamAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@ExperimentalCoroutinesApi
@Module
@InstallIn(SingletonComponent::class)
object ChatModule {

//    @Provides
//    @Singleton
//    fun provideScarlet(gson: Gson,client: OkHttpClient): Scarlet {
//        return Scarlet.Builder()
//            .addMessageAdapterFactory(CustomGsonMessageAdapter.Factory(gson))
//            .addStreamAdapterFactory(CoroutinesStreamAdapterFactory())
//            .webSocketFactory(
//                client.newWebSocketFactory("ws://192.168.0.105:8081/api/chat/websocket")
//            )
//            .backoffStrategy(LinearBackoffStrategy(Constants.RECONNECT_INTERVAL))
//            .build()
//
//    }


//    @Provides
//    @Singleton
//    fun provideChatService(scarlet: Scarlet): ChatService {
//        return scarlet.create()
//    }

    @Provides
    fun provideChatUseCases(repository: ChatRepository): ChatUseCases {
        return ChatUseCases(
            sendMessage = SendMessage(repository),
            observeChatEvents = ObserveChatEvents(repository),
            observeMessages = ObserveMessages(repository),
            getChatsForUser = GetChatsForUser(repository),
            getMessagesForChat = GetMessagesForChat(repository),
            initializeRepository = InitializeRepository(repository)
        )
    }


    @Provides
    @Singleton
    fun provideChatApi(client: OkHttpClient): ChatApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ChatApi::class.java)
    }


    @Provides
    fun provideChatRepository(client: OkHttpClient, chatApi: ChatApi): ChatRepository {
        return ChatRepositoryImpl(chatApi,client)
    }
}