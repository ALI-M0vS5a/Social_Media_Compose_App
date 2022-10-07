package com.example.socialmedia.feature_profile.presentation.search

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.socialmedia.feature_profile.domain.use_case.ProfileUseCases
import com.example.socialmedia.feature_profile.domain.util.ProfileConstants
import com.example.socialmedia.util.Resource
import com.example.socialmedia.util.UiEvent
import com.example.socialmedia.util.UiText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val profileUseCases: ProfileUseCases
) : ViewModel() {

    private val _uiState = mutableStateOf(SearchScreenState())
    val uiState: State<SearchScreenState> = _uiState

    private var searchJob: Job? = null

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    fun onEvent(event: SearchEvent) {
        when (event) {
            is SearchEvent.Query -> {
                searchUser(event.query)
            }
            is SearchEvent.ToggleFollowState -> {
                toggleFollowStateForUser(event.userId)
            }
        }
    }

    private fun toggleFollowStateForUser(userId: String) {
        viewModelScope.launch {

            val isFollowing = uiState.value.userItem.find {
                it.userId == userId
            }?.isFollowing == true

            _uiState.value = uiState.value.copy(
                userItem = uiState.value.userItem.map {
                    if(it.userId == userId) {
                        it.copy(isFollowing = !it.isFollowing)
                    } else it
                }
            )
            val result = profileUseCases.toggleFollowStateForUser(
                userId = userId,
                isFollowing = isFollowing
            )

            when(result) {
                is Resource.Success -> Unit
                is Resource.Error -> {
                    _uiState.value = uiState.value.copy(
                        userItem = uiState.value.userItem.map {
                            if(it.userId == userId) {
                                it.copy(isFollowing = isFollowing)
                            } else it
                        }
                    )
                    _eventFlow.emit(UiEvent.Message(result.uiText ?: UiText.unknownError()))
                }
            }
        }
    }

    private fun searchUser(query: String) {
        _uiState.value = uiState.value.copy(
            search = query
        )
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            delay(ProfileConstants.SEARCH_DELAY)
            _uiState.value = uiState.value.copy(
                isLoading = true
            )
            when (val result = profileUseCases.searchUser(query)) {
                is Resource.Success -> {
                    _uiState.value = uiState.value.copy(
                        userItem = result.data ?: emptyList(),
                        isLoading = false
                    )
                }
                is Resource.Error -> {
                    _uiState.value = uiState.value.copy(
                        error = SearchError(
                            message = result.uiText ?: UiText.unknownError()
                        ),
                        isLoading = false
                    )
                }
            }
        }
    }
}