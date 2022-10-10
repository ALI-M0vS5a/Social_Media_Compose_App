package com.example.socialmedia.feature_post.domian.use_case

import com.example.socialmedia.domain.models.Post
import com.example.socialmedia.feature_post.domian.repository.PostRepository
import com.example.socialmedia.util.Resource


class GetPostDetailsUseCase(
    private val repository: PostRepository
) {
    suspend operator fun invoke(postId: String): Resource<Post> {
        return repository.getPostDetails(postId)
    }
}