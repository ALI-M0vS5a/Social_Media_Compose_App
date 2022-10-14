package com.example.socialmedia.feature_chat.presentation.chat

import android.util.Base64
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.socialmedia.feature_chat.presentation.components.ChatItem
import com.example.socialmedia.presentation.util.Screen

@ExperimentalMaterial3Api
@Composable
fun ChatScreen(
    onNavigate: (String) -> Unit = {},
    viewModel: ChatViewModel = hiltViewModel()
) {
    BackHandler {
        onNavigate(Screen.MainFeedScreen.route)
    }
    val chats = viewModel.uiState.value.chats
    val isLoading = viewModel.uiState.value.isLoading
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
                        onNavigate(
                            Screen.MessageScreen.route + "/${chat.remoteUserId}/${chat.remoteUsername}/${
                                Base64.encodeToString(
                                    chat.remoteUserProfilePictureUrl.encodeToByteArray(),
                                    0
                                )
                            }?chatId=${chat.chatId}"
                        )
                    }
                )
            }
        }
    }
}
