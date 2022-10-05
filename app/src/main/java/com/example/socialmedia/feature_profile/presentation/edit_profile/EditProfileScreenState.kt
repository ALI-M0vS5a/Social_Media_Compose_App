package com.example.socialmedia.feature_profile.presentation.edit_profile

import android.net.Uri
import com.example.socialmedia.feature_profile.domain.model.Profile
import com.example.socialmedia.feature_profile.domain.model.Skill


data class EditProfileScreenState(
    val username: String = "",
    val github: String = "",
    val instagram: String = "",
    val linkedin: String = "",
    val yourBio: String = "",
    val selectedSkills: List<Skill> = emptyList(),
    val skills: List<Skill> = emptyList(),
    val profile: Profile? = null,
    val isLoading: Boolean = false,
    val bannerUri: Uri? = null,
    val profilePictureUri: Uri? = null
)
