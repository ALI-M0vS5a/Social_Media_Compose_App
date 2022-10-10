package com.example.socialmedia.feature_post.domian.use_case

import com.example.socialmedia.domain.models.Comment
import com.example.socialmedia.feature_post.domian.repository.PostRepository
import com.example.socialmedia.util.Resource


class GetCommentsForPostUseCase(
    private val repository: PostRepository
) {
    suspend operator fun invoke(postId: String): Resource<List<Comment>> {
        return repository.getCommentsForPost(postId)
    }
}