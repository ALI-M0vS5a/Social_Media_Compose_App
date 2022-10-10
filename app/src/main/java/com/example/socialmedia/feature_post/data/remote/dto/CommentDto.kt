package com.example.socialmedia.feature_post.data.remote.dto

import com.example.socialmedia.domain.models.Comment
import java.text.SimpleDateFormat
import java.util.*

data class CommentDto(
    val id: String,
    val username: String,
    val profileImageUrl: String,
    val timeStamp: Long,
    val comment: String,
    val isLiked: Boolean,
    val likeCount: Int
) {
    fun toComment(): Comment {
        return Comment(
            commentId = id,
            username = username,
            profileImageUrl = profileImageUrl,
            formattedTime = SimpleDateFormat("MMM dd, HH:mm", Locale.getDefault()).run {
                format(timeStamp)
            },
            comment = comment,
            isLiked = isLiked,
            likeCount = likeCount
        )
    }
}