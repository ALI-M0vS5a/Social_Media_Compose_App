package com.example.socialmedia.feature_chat.domain.di

import com.example.socialmedia.feature_chat.data.remote.ChatService
import com.example.socialmedia.feature_chat.data.remote.util.CustomGsonMessageAdapter
import com.example.socialmedia.util.Constants
import com.google.gson.Gson
import com.tinder.scarlet.Scarlet
import com.tinder.scarlet.retry.LinearBackoffStrategy
import com.tinder.scarlet.websocket.okhttp.newWebSocketFactory
import com.tinder.streamadapter.coroutines.CoroutinesStreamAdapterFactory
import okhttp3.OkHttpClient


object ScarletInstance {

    var current: ChatService? = null

    fun getNewInstance(client: OkHttpClient): ChatService {
        return Scarlet.Builder()
            .addMessageAdapterFactory(CustomGsonMessageAdapter.Factory(Gson()))
            .addStreamAdapterFactory(CoroutinesStreamAdapterFactory())
            .webSocketFactory(
                client.newWebSocketFactory(
                    "ws://192.168.0.105:8081/api/chat/websocket"
                )
            )
            .backoffStrategy(LinearBackoffStrategy(Constants.RECONNECT_INTERVAL))
            .build()
            .create(ChatService::class.java)
            .also {
                current = it
            }
    }
}