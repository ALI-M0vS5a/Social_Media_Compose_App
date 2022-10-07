package com.example.socialmedia.feature_profile.domain.use_case

import com.example.socialmedia.feature_profile.domain.repository.ProfileRepository
import com.example.socialmedia.util.SimpleResource


class ToggleFollowStateForUserUseCase(
    private val repository: ProfileRepository
) {
    suspend operator fun invoke(userId: String, isFollowing: Boolean): SimpleResource {
        return if(isFollowing) {
            repository.unfollowUser(userId)
        } else {
            repository.followUser(userId)
        }
    }
}