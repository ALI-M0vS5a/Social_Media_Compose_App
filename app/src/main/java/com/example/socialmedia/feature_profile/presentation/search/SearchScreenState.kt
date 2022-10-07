package com.example.socialmedia.feature_profile.presentation.search

import com.example.socialmedia.domain.models.UserItem
import com.example.socialmedia.util.UiText


data class SearchScreenState(
    val search: String = "",
    val error: SearchError = SearchError(UiText.unknownError()),
    val userItem: List<UserItem> = emptyList(),
    val isLoading: Boolean = false
)
