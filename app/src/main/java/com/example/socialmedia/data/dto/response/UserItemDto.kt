package com.example.socialmedia.data.dto.response

import com.example.socialmedia.domain.models.UserItem


data class UserItemDto(
    val userId: String,
    val username: String,
    val profilePictureUrl: String,
    val bio: String,
    val isFollowing: Boolean
) {
    fun toUserItem(): UserItem {
        return UserItem(
            userId = userId,
            username = username,
            profilePictureUrl = profilePictureUrl,
            bio = bio,
            isFollowing = isFollowing
        )
    }
}
