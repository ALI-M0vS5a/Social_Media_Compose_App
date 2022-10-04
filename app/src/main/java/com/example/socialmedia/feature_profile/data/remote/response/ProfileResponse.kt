package com.example.socialmedia.feature_profile.data.remote.response

import com.example.socialmedia.feature_profile.domain.Profile

data class ProfileResponse(
    val userId: String,
    val username: String,
    val bio: String,
    val followerCount: Int,
    val followingCount: Int,
    val postCount: Int,
    val profilePictureUrl: String,
    val topSkillUrls: List<String>,
    val gitHubUrl: String?,
    val instagramUrl: String?,
    val linkedinUrl: String?,
    val isOwnProfile: Boolean,
    val isFollowing: Boolean
){
    fun toProfile(): Profile {
        return Profile(
            userId = userId,
            username = username,
            bio = bio,
            followerCount = followerCount,
            followingCount = followingCount,
            postCount = postCount,
            profilePictureUrl = profilePictureUrl,
            topSkillUrls = topSkillUrls,
            gitHubUrl = gitHubUrl,
            instagramUrl = instagramUrl,
            linkedinUrl = linkedinUrl,
            isOwnProfile = isOwnProfile,
            isFollowing = isFollowing
        )
    }
}
