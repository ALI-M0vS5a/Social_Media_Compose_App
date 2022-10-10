package com.example.socialmedia.feature_post.presentation.main_feed


sealed class MainFeedEvents {
    data class LikedPost(val postId: String): MainFeedEvents()
}