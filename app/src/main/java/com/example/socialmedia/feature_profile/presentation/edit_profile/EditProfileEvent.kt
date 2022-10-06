package com.example.socialmedia.feature_profile.presentation.edit_profile

import android.net.Uri
import com.example.socialmedia.feature_profile.domain.model.Skill


sealed class EditProfileEvent {
    data class EnteredUsername(val username: String): EditProfileEvent()
    data class EnteredGitHubUrl(val gitHubUrl: String): EditProfileEvent()
    data class EnteredInstagramUrl(val instagram: String): EditProfileEvent()
    data class EnteredLinkedinUrl(val linkedin: String): EditProfileEvent()
    data class EnteredBio(val bio: String): EditProfileEvent()

    data class CropProfilePicture(val pictureUri: Uri?): EditProfileEvent()
    data class CropBannerImage(val bannerUri: Uri?): EditProfileEvent()

    data class SetSkillSelected(val skill: Skill): EditProfileEvent()

    object UpdateProfile: EditProfileEvent()
}
