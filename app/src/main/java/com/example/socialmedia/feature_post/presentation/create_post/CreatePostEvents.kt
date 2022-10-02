package com.example.socialmedia.feature_post.presentation.create_post

import android.net.Uri


sealed class CreatePostEvents {
    data class EnterDescription(val value: String): CreatePostEvents()
    data class PickImage(val uri: Uri?): CreatePostEvents()
    data class CropImage(val uri: Uri?): CreatePostEvents()
    object PostImage: CreatePostEvents()
}
