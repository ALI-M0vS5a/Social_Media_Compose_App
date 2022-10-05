package com.example.socialmedia.feature_profile.domain.use_case

import android.net.Uri
import com.example.socialmedia.R
import com.example.socialmedia.feature_profile.domain.model.UpdateProfileData
import com.example.socialmedia.feature_profile.domain.repository.ProfileRepository
import com.example.socialmedia.util.Resource
import com.example.socialmedia.util.SimpleResource
import com.example.socialmedia.util.UiText


class UpdateProfileUseCase(
    private val repository: ProfileRepository
) {
    suspend operator fun invoke(
        updateProfileData: UpdateProfileData,
        profilePictureUri: Uri?,
        bannerPictureUri: Uri?
    ): SimpleResource {
        if(updateProfileData.username.isBlank()) {
            return Resource.Error(
                UiText.StringResource(R.string.error_username_empty)
            )
        }
        return repository.updateProfile(
            updateProfileData = updateProfileData,
            profilePictureUri = profilePictureUri,
            bannerImageUri = bannerPictureUri
        )
    }
}