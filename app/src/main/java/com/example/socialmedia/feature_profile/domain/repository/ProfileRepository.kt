package com.example.socialmedia.feature_profile.domain.repository

import com.example.socialmedia.feature_profile.domain.Profile
import com.example.socialmedia.util.Resource


interface ProfileRepository {

    suspend fun getProfile(userId: String): Resource<Profile>
}