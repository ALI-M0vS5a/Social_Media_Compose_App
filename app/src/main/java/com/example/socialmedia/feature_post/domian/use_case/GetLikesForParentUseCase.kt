package com.example.socialmedia.feature_post.domian.use_case

import com.example.socialmedia.domain.models.UserItem
import com.example.socialmedia.feature_post.domian.repository.PostRepository
import com.example.socialmedia.util.Resource

class GetLikesForParentUseCase(
    private val repository: PostRepository
) {

    suspend operator fun invoke(parentId: String): Resource<List<UserItem>> {
        return repository.getLikesForParent(parentId)
    }
}