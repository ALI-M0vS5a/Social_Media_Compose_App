package com.example.socialmedia.feature_post.presentation.main_feed

import com.example.socialmedia.domain.models.Post


data class MainFeedState(
    val posts: List<Post> = emptyList(),
    val isLoadingFirstTime: Boolean = true,
    val isLoadingNewPosts: Boolean = false
)
