package com.example.socialmedia.feature_profile.domain.repository

import android.net.Uri
import com.example.socialmedia.feature_profile.domain.model.Profile
import com.example.socialmedia.feature_profile.domain.model.Skill
import com.example.socialmedia.feature_profile.domain.model.UpdateProfileData
import com.example.socialmedia.util.Resource
import com.example.socialmedia.util.SimpleResource


interface ProfileRepository {

    suspend fun getProfile(userId: String): Resource<Profile>

    suspend fun getSkills(): Resource<List<Skill>>

    suspend fun updateProfile(
        updateProfileData: UpdateProfileData,
        bannerImageUri: Uri?,
        profilePictureUri: Uri?
    ): SimpleResource
}