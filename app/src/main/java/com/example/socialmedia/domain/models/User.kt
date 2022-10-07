package com.example.socialmedia.domain.models


data class User(
    val userId: String,
    val profilePictureUrl: String,
    val bannerUrl: String?,
    val username: String,
    val description: String,
    val followerCount: Int,
    val followingCount: Int,
    val postCount: Int
)
