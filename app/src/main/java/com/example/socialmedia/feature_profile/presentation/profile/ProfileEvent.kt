package com.example.socialmedia.feature_profile.presentation.profile


sealed class ProfileEvent {
    object DismissLogoutDialog: ProfileEvent()
    object ShowLogoutDialog: ProfileEvent()
    object Logout: ProfileEvent()
}
