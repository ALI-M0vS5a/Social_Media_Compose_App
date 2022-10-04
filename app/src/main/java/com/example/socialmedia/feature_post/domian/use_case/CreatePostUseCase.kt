package com.example.socialmedia.feature_post.domian.use_case

import android.net.Uri
import com.example.socialmedia.R
import com.example.socialmedia.feature_post.domian.repository.PostRepository
import com.example.socialmedia.util.Resource
import com.example.socialmedia.util.SimpleResource
import com.example.socialmedia.util.UiText

class CreatePostUseCase(
    private val repository: PostRepository
) {
    suspend operator fun invoke(
        description: String,
        imageUri: Uri?
    ): SimpleResource {
        if(imageUri == null) {
            return Resource.Error(
                UiText.StringResource(R.string.pick_image)
            )
        }
        if(description.isBlank()) {
            return Resource.Error(
                UiText.StringResource(R.string.error_description_blank)
            )
        }
        return repository.createPost(description,imageUri)
    }
}