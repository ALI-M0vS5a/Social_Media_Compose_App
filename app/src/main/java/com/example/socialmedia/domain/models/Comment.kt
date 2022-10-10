package com.example.socialmedia.domain.models


data class Comment(
    val commentId: String,
    val username: String,
    val profileImageUrl: String,
    val formattedTime: String,
    val comment: String,
    val isLiked: Boolean,
    val likeCount: Int
)
