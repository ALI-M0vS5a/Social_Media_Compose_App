package com.example.socialmedia.feature_profile.domain.model


data class Profile(
    val userId: String,
    val username: String,
    val bio: String,
    val followerCount: Int,
    val followingCount: Int,
    val postCount: Int,
    val profilePictureUrl: String,
    val bannerUrl: String,
    val topSkills: List<Skill>,
    val gitHubUrl: String?,
    val instagramUrl: String?,
    val linkedinUrl: String?,
    val isOwnProfile: Boolean,
    val isFollowing: Boolean
)
