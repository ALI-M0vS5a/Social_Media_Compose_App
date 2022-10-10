package com.example.socialmedia.feature_post.domian.use_case

import com.example.socialmedia.feature_post.domian.repository.PostRepository
import com.example.socialmedia.util.SimpleResource


class ToggleLikeForParentUseCase(
    private val repository: PostRepository
) {
    suspend operator fun invoke(parentId: String, parentType: Int, isLiked: Boolean): SimpleResource {
        return if(isLiked) {
            repository.unlikeParent(parentId, parentType)
        } else {
            repository.likeParent(parentId,parentType)
        }
    }
}