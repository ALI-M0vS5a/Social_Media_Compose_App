package com.example.socialmedia.feature_profile.presentation.person_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.socialmedia.feature_post.domian.use_case.PostUseCases
import com.example.socialmedia.feature_profile.domain.use_case.ProfileUseCases
import com.example.socialmedia.feature_profile.domain.use_case.ToggleFollowStateForUserUseCase
import com.example.socialmedia.use_case.GetOwnUserIdUseCase
import com.example.socialmedia.util.Resource
import com.example.socialmedia.util.UiEvent
import com.example.socialmedia.util.UiText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class PersonListViewModel @Inject constructor(
    private val postUseCases: PostUseCases,
    private val profileUseCases: ProfileUseCases,
    private val getOwnUserId: GetOwnUserIdUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _uiState = mutableStateOf(PersonListScreenState())
    val uiState: State<PersonListScreenState> = _uiState

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()


    init {
        savedStateHandle.get<String>("parentId")?.let { parentId ->
            getLikesForParent(parentId)
        }
    }


    fun onEvent(event: PersonListEvent) {
        when(event) {
            is PersonListEvent.ToggleFollowStateForUser -> {
                toggleFollowStateForUser(event.userId)
                _uiState.value = uiState.value.copy(
                    ownUserId = getOwnUserId()
                )
            }
        }
    }

    private fun getLikesForParent(parentId: String) {
        viewModelScope.launch {
            _uiState.value = uiState.value.copy(
                isLoading = true
            )
            val result = postUseCases.getLikesForParent(parentId)
            when (result) {
                is Resource.Success -> {
                    _uiState.value = uiState.value.copy(
                        users = result.data ?: emptyList(),
                        isLoading = false
                    )
                }

                is Resource.Error -> {
                    _uiState.value = uiState.value.copy(
                        isLoading = false
                    )
                    _eventFlow.emit(
                        UiEvent.Message(result.uiText ?: UiText.unknownError())
                    )
                }
            }
        }
    }

    private fun toggleFollowStateForUser(userId: String) {
        viewModelScope.launch {

            val isFollowing = uiState.value.users.find {
                it.userId == userId
            }?.isFollowing == true

            _uiState.value = uiState.value.copy(
                users = uiState.value.users.map {
                    if (it.userId == userId) {
                        it.copy(isFollowing = !it.isFollowing)
                    } else it
                }
            )
            val result = profileUseCases.toggleFollowStateForUser(
                userId = userId,
                isFollowing = isFollowing
            )

            when (result) {
                is Resource.Success -> Unit
                is Resource.Error -> {
                    _uiState.value = uiState.value.copy(
                        users = uiState.value.users.map {
                            if (it.userId == userId) {
                                it.copy(isFollowing = isFollowing)
                            } else it
                        }
                    )
                    _eventFlow.emit(UiEvent.Message(result.uiText ?: UiText.unknownError()))
                }
            }
        }
    }
}
