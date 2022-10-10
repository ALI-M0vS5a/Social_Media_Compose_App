package com.example.socialmedia.feature_profile.presentation.person_list

import com.example.socialmedia.domain.models.UserItem


data class PersonListScreenState(
    val users: List<UserItem> = emptyList(),
    val isLoading: Boolean = true,
    val ownUserId: String = ""
)
