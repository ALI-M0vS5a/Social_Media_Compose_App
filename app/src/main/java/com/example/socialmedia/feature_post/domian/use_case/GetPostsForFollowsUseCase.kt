package com.example.socialmedia.feature_post.domian.use_case

import androidx.paging.PagingData
import com.example.socialmedia.domain.models.Post
import com.example.socialmedia.feature_post.domian.repository.PostRepository
import kotlinx.coroutines.flow.Flow


class GetPostsForFollowsUseCase(
    private val repository: PostRepository
) {
    operator fun invoke(
    ): Flow<PagingData<Post>> {
        return repository.posts
    }
}