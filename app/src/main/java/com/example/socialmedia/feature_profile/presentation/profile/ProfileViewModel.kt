package com.example.socialmedia.feature_profile.presentation.profile

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.socialmedia.domain.models.Post
import com.example.socialmedia.feature_profile.domain.use_case.ProfileUseCases
import com.example.socialmedia.use_case.GetOwnUserIdUseCase
import com.example.socialmedia.util.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val profileUseCases: ProfileUseCases,
    private val getOwnUserId: GetOwnUserIdUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf(ProfileState())
    val state: State<ProfileState> = _state

    private val _eventFlow = MutableSharedFlow<Event>()
    val eventFlow = _eventFlow.asSharedFlow()



    private val _pagingState = mutableStateOf<PostPagingState<Post>>(PostPagingState())
    val pagingState: State<PostPagingState<Post>> = _pagingState

    private val paging = DefaultPaging(
        onLoadUpdated = { isLoading ->
            _pagingState.value = pagingState.value.copy(
                isLoading = isLoading
            )
        },
        onRequest = { page ->
            val userId = savedStateHandle.get<String>("userId") ?: getOwnUserId()
            profileUseCases.getPostsForProfile(
                userId = userId,
                page = page
            )
        },
        onSuccess = { posts ->
            _pagingState.value = pagingState.value.copy(
                items = pagingState.value.items + posts,
                endReached = posts.isEmpty(),
                isLoading = false
            )
        },
        onError = { uiText ->
            _eventFlow.emit(UiEvent.Message(uiText))
        }
    )

    init {
        loadNextPosts()
    }

    fun loadNextPosts() {
        viewModelScope.launch {
            paging.loadNextItems()
        }
    }

    fun onEvent(event: ProfileEvent) {
        when (event) {
            is ProfileEvent.GetProfile -> {

            }
        }
    }

    fun getProfile(userId: String?) {
        viewModelScope.launch {
            _state.value = state.value.copy(
                isLoading = true
            )
            when (val result = profileUseCases.getProfile(userId ?: getOwnUserId())) {
                is Resource.Success -> {
                    println("userId is from getProfile ${result.data?.userId}")
                    _state.value = state.value.copy(
                        profile = result.data,
                        isLoading = false
                    )
                }
                is Resource.Error -> {
                    _state.value = state.value.copy(
                        isLoading = false
                    )
                    _eventFlow.emit(
                        UiEvent.Message(
                            uiText = result.uiText ?: UiText.unknownError()
                        )
                    )
                }
            }
        }
    }
}