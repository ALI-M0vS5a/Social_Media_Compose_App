package com.example.socialmedia.feature_post.presentation.post_detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.socialmedia.R
import com.example.socialmedia.feature_post.domian.use_case.PostUseCases
import com.example.socialmedia.feature_post.presentation.util.CommentError
import com.example.socialmedia.feature_post.presentation.util.ParentType
import com.example.socialmedia.util.Resource
import com.example.socialmedia.util.UiEvent
import com.example.socialmedia.util.UiText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostDetailViewModel @Inject constructor(
    private val postUseCases: PostUseCases,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _uiState = mutableStateOf(PostDetailState())
    val uiState: State<PostDetailState> = _uiState

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    init {
        savedStateHandle.get<String>("postId")?.let { postId ->
            loadPostDetails(postId)
            loadCommentsForPost(postId)
        }
    }

    fun onEvent(event: PostDetailEvent) {
        when (event) {
            is PostDetailEvent.LikePost -> {
                val isLiked = uiState.value.post?.isLiked == true
                toggleLikeForParent(
                    parentId = uiState.value.post?.id ?: return,
                    parentType = ParentType.Post.type,
                    isLiked = isLiked
                )
            }
            is PostDetailEvent.Comment -> {
                createComment(
                    postId = savedStateHandle.get<String>("postId") ?: "",
                    comment = uiState.value.CommentTextField
                )
            }
            is PostDetailEvent.SharePost -> {

            }
            is PostDetailEvent.LikeComment -> {
                val isLiked = uiState.value.comments.find {
                    it.commentId == event.commentId
                }?.isLiked == true
                toggleLikeForParent(
                    parentId = event.commentId,
                    parentType = ParentType.Comment.type,
                    isLiked = isLiked
                )
            }
            is PostDetailEvent.EnteredComment -> {
                _uiState.value = uiState.value.copy(
                    CommentTextField = event.comment,
                    error = if (event.comment.isBlank()) CommentError.FieldEmpty else null
                )
            }
        }
    }

    private fun toggleLikeForParent(
        parentId: String,
        parentType: Int,
        isLiked: Boolean
    ) {
        viewModelScope.launch {
            when (parentType) {
                ParentType.Post.type -> {
                    _uiState.value = uiState.value.copy(
                        post = uiState.value.post?.copy(
                            isLiked = !isLiked
                        )
                    )
                }
                ParentType.Comment.type -> {
                    _uiState.value = uiState.value.copy(
                        comments = uiState.value.comments.map {
                            if (it.commentId == parentId) {
                                it.copy(isLiked = !isLiked)
                            } else it
                        }
                    )
                }
            }
            val result = postUseCases.toggleLikeForParent(
                parentId = parentId,
                parentType = parentType,
                isLiked = isLiked
            )
            when (result) {
                is Resource.Success -> Unit

                is Resource.Error -> {
                    when (parentType) {
                        ParentType.Post.type -> {
                            _uiState.value = uiState.value.copy(
                                post = uiState.value.post?.copy(
                                    isLiked = isLiked
                                )
                            )
                        }
                        ParentType.Comment.type -> {
                            _uiState.value = uiState.value.copy(
                                comments = uiState.value.comments.map {
                                    if (it.commentId == parentId) {
                                        it.copy(isLiked = isLiked)
                                    } else it
                                }
                            )
                        }
                    }
                }
            }
        }
    }

    private fun createComment(postId: String, comment: String) {
        viewModelScope.launch {
            _uiState.value = uiState.value.copy(
                isLoadingCreateComment = true
            )
            val result = postUseCases.createComment(
                postId = postId,
                comment = comment
            )
            when (result) {
                is Resource.Success -> {
                    _eventFlow.emit(
                        UiEvent.Message(
                            result.uiText ?: UiText.StringResource(R.string.comment_posted)
                        )
                    )
                    _uiState.value = uiState.value.copy(
                        isLoadingCreateComment = false
                    )
                    _uiState.value = uiState.value.copy(
                        CommentTextField = "",
                        error = CommentError.FieldEmpty
                    )
                    loadCommentsForPost(postId)
                }

                is Resource.Error -> {
                    _eventFlow.emit(
                        UiEvent.Message(result.uiText ?: UiText.unknownError())
                    )
                    _uiState.value = uiState.value.copy(
                        isLoadingCreateComment = false
                    )
                }
            }
        }
    }

    private fun loadPostDetails(postId: String) {
        viewModelScope.launch {
            _uiState.value = uiState.value.copy(
                isLoadingPost = true
            )
            val result = postUseCases.getPostDetails(postId)
            when (result) {
                is Resource.Success -> {
                    _uiState.value = uiState.value.copy(
                        post = result.data,
                        isLoadingPost = false
                    )
                }
                is Resource.Error -> {
                    _uiState.value = uiState.value.copy(
                        isLoadingPost = false
                    )
                    _eventFlow.emit(
                        UiEvent.Message(
                            result.uiText ?: UiText.unknownError()
                        )
                    )
                }
            }
        }
    }

    private fun loadCommentsForPost(postId: String) {
        viewModelScope.launch {
            _uiState.value = uiState.value.copy(
                isLoadingComments = true
            )
            val result = postUseCases.getCommentsForPost(postId)
            when (result) {
                is Resource.Success -> {
                    _uiState.value = uiState.value.copy(
                        comments = result.data ?: emptyList(),
                        isLoadingComments = false
                    )
                }
                is Resource.Error -> {
                    _uiState.value = uiState.value.copy(
                        isLoadingComments = false
                    )
                }
            }
        }
    }
}