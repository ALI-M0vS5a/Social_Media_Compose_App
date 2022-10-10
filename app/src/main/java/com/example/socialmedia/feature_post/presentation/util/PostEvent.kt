package com.example.socialmedia.feature_post.presentation.util

import com.example.socialmedia.util.Event


sealed class PostEvent: Event() {
    object OnLiked: PostEvent()
}
