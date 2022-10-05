package com.example.socialmedia.feature_profile.data.remote.response

import com.example.socialmedia.feature_profile.domain.model.Profile

data class ProfileResponse(
    val userId: String,
    val username: String,
    val bio: String,
    val followerCount: Int,
    val followingCount: Int,
    val postCount: Int,
    val profilePictureUrl: String,
    val bannerUrl: String,
    val topSkills: List<SkillDto>,
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
            bannerUrl = bannerUrl,
            topSkills = topSkills.map { it.toSkill() },
            gitHubUrl = gitHubUrl,
            instagramUrl = instagramUrl,
            linkedinUrl = linkedinUrl,
            isOwnProfile = isOwnProfile,
            isFollowing = isFollowing
        )
    }
}
