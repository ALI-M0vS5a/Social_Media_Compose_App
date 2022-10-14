package com.example.socialmedia.feature_chat.presentation.chat

import androidx.compose.runtime.State
import androidx.compose.runtime.isTraceInProgress
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.socialmedia.feature_chat.domain.use_case.ChatUseCases
import com.example.socialmedia.util.Resource
import com.example.socialmedia.util.UiEvent
import com.example.socialmedia.util.UiText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val chatUseCases: ChatUseCases
) : ViewModel() {

    private val _uiState = mutableStateOf(ChatScreenState())
    val uiState: State<ChatScreenState> = _uiState

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    init {
        loadChats()
    }

    private fun loadChats() {
        viewModelScope.launch {
            _uiState.value = uiState.value.copy(
                isLoading = true
            )
            when(val result = chatUseCases.getChatsForUser()) {
                is Resource.Success -> {
                    _uiState.value = uiState.value.copy(
                        chats = result.data ?: emptyList(),
                        isLoading = false
                    )
                }

                is Resource.Error -> {
                    _eventFlow.emit(
                        UiEvent.Message(result.uiText ?: UiText.unknownError())
                    )
                }
            }
        }
    }
}