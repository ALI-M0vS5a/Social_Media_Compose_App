package com.example.socialmedia.feature_chat.presentation.message

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.socialmedia.feature_chat.domain.model.Message
import com.example.socialmedia.feature_chat.domain.use_case.ChatUseCases
import com.example.socialmedia.presentation.util.states.StandardTextFieldState
import com.example.socialmedia.util.*
import com.tinder.scarlet.WebSocket
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import javax.inject.Inject

@HiltViewModel
class MessageScreenViewModel @Inject constructor(
    private val chatUseCases: ChatUseCases,
    private val savedStateHandle: SavedStateHandle,
    private val client: OkHttpClient
) : ViewModel() {

    private val _messageTextFieldState = mutableStateOf(StandardTextFieldState())
    val messageTextFieldState: State<StandardTextFieldState> = _messageTextFieldState


    private val _uiState = mutableStateOf(MessageScreenState())
    val uiState: State<MessageScreenState> = _uiState

    private val _pagingState = mutableStateOf<PostPagingState<Message>>(PostPagingState())
    val pagingState: State<PostPagingState<Message>> = _pagingState

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private val _messageUpdatedEvent = MutableSharedFlow<MessageUpdateEvent>(replay = 1)
    val messageReceived = _messageUpdatedEvent.asSharedFlow()

    private val paging = DefaultPaging(
        onLoadUpdated = { isLoading ->
            _pagingState.value = pagingState.value.copy(isLoading = isLoading)
        },
        onRequest = { nextPage ->
            savedStateHandle.get<String>("chatId")?.let { chatId ->
                chatUseCases.getMessagesForChat(
                    chatId, nextPage
                )
            } ?: Resource.Error(UiText.unknownError())
        },
        onError = { errorUiText ->
            _eventFlow.emit(UiEvent.Message(errorUiText))
        },
        onSuccess = { messages ->
           _pagingState.value = pagingState.value.copy(
               items = pagingState.value.items + messages,
               endReached = messages.isEmpty(),
               isLoading = false
           )
            viewModelScope.launch {
                _messageUpdatedEvent.emit(MessageUpdateEvent.MessagePageLoaded)
            }
        }
    )

    init {
        chatUseCases.initializeRepository()
        loadNextMessages()
        observeChatEvents()
        observeChatMessages()
    }

    fun onEvent(event: MessageEvent) {
        when(event) {
            is MessageEvent.EnteredMessage -> {
                _messageTextFieldState.value = messageTextFieldState.value.copy(
                    text = event.message
                )
            }

            is MessageEvent.SendMessage -> {
                sendMessage()
            }
        }
    }

    private fun observeChatMessages() {
        chatUseCases.observeMessages()
            .onEach { message ->
                _pagingState.value = pagingState.value.copy(
                    items = pagingState.value.items + message
                )
                _messageUpdatedEvent.emit(MessageUpdateEvent.SingleMessageUpdate)
            }.launchIn(viewModelScope)
    }
    private fun observeChatEvents() {
        chatUseCases.observeChatEvents()
            .onEach { event ->
                when(event) {
                    is WebSocket.Event.OnConnectionOpened<*> -> {
                        println("Connection was opened")
                    }
                    is WebSocket.Event.OnConnectionFailed -> {
                        println("Connection was failed: ${event.throwable}")
                    }
                }
            }.launchIn(viewModelScope)
    }

    private fun sendMessage() {
        val toId = savedStateHandle.get<String>("remoteUserId") ?: return
        if(messageTextFieldState.value.text.isBlank()) {
            return
        }
        val chatId = savedStateHandle.get<String>("chatId") ?: return
        chatUseCases.sendMessage(toId, messageTextFieldState.value.text, chatId)
        _messageTextFieldState.value = StandardTextFieldState()
        viewModelScope.launch {
            _messageUpdatedEvent.emit(MessageUpdateEvent.MessageSent)
        }
    }

    fun loadNextMessages() {
        viewModelScope.launch {
            paging.loadNextItems()
        }
    }

    sealed class MessageUpdateEvent {
        object SingleMessageUpdate: MessageUpdateEvent()
        object MessagePageLoaded: MessageUpdateEvent()
        object MessageSent: MessageUpdateEvent()
    }

}