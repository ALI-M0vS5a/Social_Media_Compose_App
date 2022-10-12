package com.example.socialmedia.feature_chat.presentation.chat

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.socialmedia.feature_chat.components.ChatItem
import com.example.socialmedia.feature_chat.domain.model.Chat
import com.example.socialmedia.presentation.util.Screen

@ExperimentalMaterial3Api
@Composable
fun ChatScreen(
   onNavigate: (String) -> Unit = {}
) {
    BackHandler {
        onNavigate(Screen.MainFeedScreen.route)
    }
    val chats = remember {
        listOf(
            Chat(
                remoteUsername = "Selena Gomez",
                remoteUserProfileUser = "http://192.168.0.105:8081/profile_pictures/bb4c7b5b-0e3b-4b50-8509-21b2457f0530.png",
                lastMessage = "Hey, How's it going?",
                lastMessageFormattedTime = "19:39"
            ),
            Chat(
                remoteUsername = "Taylee Wood",
                remoteUserProfileUser = "http://192.168.0.105:8081/profile_pictures/bb4c7b5b-0e3b-4b50-8509-21b2457f0530.png",
                lastMessage = "Yo, are you going to the Monica's wedding?Yo, are you going to the Monica's wedding?Yo, are you going to the Monica's wedding?",
                lastMessageFormattedTime = "19:39"
            ),
            Chat(
                remoteUsername = "Pulasthi Mahashe",
                remoteUserProfileUser = "http://192.168.0.105:8081/profile_pictures/bb4c7b5b-0e3b-4b50-8509-21b2457f0530.png",
                lastMessage = "Monica said we'd be staying over for a while.. but",
                lastMessageFormattedTime = "19:39"
            )
        )
    }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(chats) { chat ->
                ChatItem(
                    item = chat,
                    onItemClick = {
                        onNavigate(Screen.MessageScreen.route)
                    }
                )
            }
        }
    }
}
