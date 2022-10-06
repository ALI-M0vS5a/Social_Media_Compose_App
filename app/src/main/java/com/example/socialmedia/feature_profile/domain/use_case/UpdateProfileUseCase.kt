package com.example.socialmedia.feature_profile.domain.use_case

import android.net.Uri
import com.example.socialmedia.R
import com.example.socialmedia.feature_profile.domain.model.UpdateProfileData
import com.example.socialmedia.feature_profile.domain.repository.ProfileRepository
import com.example.socialmedia.feature_profile.domain.util.ProfileConstants
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
        if (updateProfileData.username.isBlank()) {
            return Resource.Error(
                message = UiText.StringResource(R.string.error_username_empty)
            )
        }
        val isValidGitHubUrl = ProfileConstants.GITHUB_PROFILE_REGEX.matches(updateProfileData.gitHubUrl)
        if (!isValidGitHubUrl) {
            return Resource.Error(
                message = UiText.StringResource(R.string.error_invalid_github_url)
            )
        }
        val isValidInstagramUrl = ProfileConstants.INSTAGRAM_PROFILE_REGEX.matches(updateProfileData.instagramUrl)
        if (!isValidInstagramUrl) {
            return Resource.Error(
                message = UiText.StringResource(R.string.error_invalid_instagram_url)
            )
        }
        val isValidLinkedinUrl = ProfileConstants.LINKEDIN_PROFILE_REGEX.matches(updateProfileData.linkedinUrl)
        if (!isValidLinkedinUrl) {
            return Resource.Error(
                message = UiText.StringResource(R.string.error_invalid_linkedin_url)
            )
        }
        return repository.updateProfile(
            updateProfileData = updateProfileData,
            profilePictureUri = profilePictureUri,
            bannerImageUri = bannerPictureUri
        )
    }
}