package com.example.socialmedia.feature_post.presentation.main_feed


sealed class MainFeedEvents {
    object LoadMorePosts: MainFeedEvents()
    object LoadedPage: MainFeedEvents()
}