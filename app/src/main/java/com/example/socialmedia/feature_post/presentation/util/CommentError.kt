package com.example.socialmedia.feature_post.presentation.util

import com.example.socialmedia.util.Error


sealed class CommentError : Error() {
    object FieldEmpty: CommentError()
}
