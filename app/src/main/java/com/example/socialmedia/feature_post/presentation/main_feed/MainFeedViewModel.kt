package com.example.socialmedia.feature_post.presentation.main_feed

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.socialmedia.feature_post.domian.use_case.PostUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject


@HiltViewModel
class MainFeedViewModel @Inject constructor(
    postUseCases: PostUseCases
) : ViewModel() {

    private val _state = mutableStateOf(MainFeedState())
    val state: State<MainFeedState> = _state

    val posts = postUseCases.getPostsForFollowsUseCase()
        .cachedIn(viewModelScope)

    fun onEvents(events: MainFeedEvents) {
        when(events) {
            is MainFeedEvents.LoadMorePosts -> {
                _state.value = state.value.copy(
                    isLoadingNewPosts = true
                )
            }
            is MainFeedEvents.LoadedPage -> {
                _state.value = state.value.copy(
                    isLoadingFirstTime = false,
                    isLoadingNewPosts = false
                )
            }
        }
    }
}