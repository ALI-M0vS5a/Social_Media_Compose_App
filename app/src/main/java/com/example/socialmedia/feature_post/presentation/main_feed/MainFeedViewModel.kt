package com.example.socialmedia.feature_post.presentation.main_feed

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.socialmedia.domain.models.Post
import com.example.socialmedia.feature_post.domian.use_case.PostUseCases
import com.example.socialmedia.feature_post.presentation.util.ParentType
import com.example.socialmedia.feature_post.presentation.util.PostEvent
import com.example.socialmedia.util.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MainFeedViewModel @Inject constructor(
    private val postUseCases: PostUseCases,
    private val postLiker: PostLiker
) : ViewModel() {

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
            postUseCases.getPostsForFollows(page = page)
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


    fun onEvents(events: MainFeedEvents) {
        when (events) {
            is MainFeedEvents.LikedPost -> {
                viewModelScope.launch {
                    toggleLikeForParent(
                        parentId = events.postId
                    )
                }
            }
        }
    }

    fun loadNextPosts() {
        viewModelScope.launch {
            paging.loadNextItems()
        }
    }

    private fun toggleLikeForParent(
        parentId: String
    ) {
        viewModelScope.launch {
            postLiker.toggleLike(
                posts = pagingState.value.items,
                parentId = parentId,
                onRequest = { isLiked ->
                    postUseCases.toggleLikeForParent(
                        parentId = parentId,
                        parentType = ParentType.Post.type,
                        isLiked
                    )
                },
                onStateUpdated = { posts ->
                    _pagingState.value = pagingState.value.copy(
                        items = posts
                    )
                }
            )
        }
    }
}