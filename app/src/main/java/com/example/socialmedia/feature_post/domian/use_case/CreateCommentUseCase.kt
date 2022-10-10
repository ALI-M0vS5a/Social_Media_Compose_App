package com.example.socialmedia.feature_post.domian.use_case

import com.example.socialmedia.R
import com.example.socialmedia.feature_post.domian.repository.PostRepository
import com.example.socialmedia.util.Resource
import com.example.socialmedia.util.SimpleResource
import com.example.socialmedia.util.UiText


class CreateCommentUseCase(
    private val repository: PostRepository
) {
    suspend operator fun invoke(postId: String, comment: String): SimpleResource {
        if(comment.isBlank()) {
            return Resource.Error(UiText.StringResource(R.string.this_field_cant_be_empty))
        }
        if(postId.isBlank()) {
            return Resource.Error(UiText.unknownError())
        }
        return repository.createComment(postId, comment)
    }
}