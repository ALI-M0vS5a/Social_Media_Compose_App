package com.example.socialmedia.feature_profile.presentation.person_list


sealed class PersonListEvent {
    data class ToggleFollowStateForUser(val userId: String): PersonListEvent()
}
