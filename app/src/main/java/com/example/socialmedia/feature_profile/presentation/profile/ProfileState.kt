package com.example.socialmedia.feature_profile.presentation.profile

import com.example.socialmedia.feature_profile.domain.model.Profile

data class ProfileState(
    val profile: Profile? = null,
    val isLoading: Boolean = false
)
