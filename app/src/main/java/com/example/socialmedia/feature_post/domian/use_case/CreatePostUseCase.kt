package com.example.socialmedia.feature_post.domian.use_case

import android.net.Uri
import com.example.socialmedia.feature_post.domian.repository.PostRepository
import com.example.socialmedia.util.SimpleResource

class CreatePostUseCase(
    private val repository: PostRepository
) {
    suspend operator fun invoke(
        description: String,
        imageUri: Uri
    ): SimpleResource {
        return repository.createPost(description,imageUri)
    }
}