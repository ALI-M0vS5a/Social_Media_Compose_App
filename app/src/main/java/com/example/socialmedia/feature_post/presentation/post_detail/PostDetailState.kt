package com.example.socialmedia.feature_post.presentation.post_detail

import com.example.socialmedia.domain.models.Comment
import com.example.socialmedia.domain.models.Post
import com.example.socialmedia.feature_post.presentation.util.CommentError

data class PostDetailState(
    val post: Post? = null,
    val comments: List<Comment> = emptyList(),
    val isLoadingPost: Boolean = false,
    val isLoadingComments: Boolean = false,
    val CommentTextField: String = "",
    val error: CommentError.FieldEmpty? = CommentError.FieldEmpty,
    val isLoadingCreateComment: Boolean = false
)
