package com.example.socialmedia.feature_post.domian.use_case

import androidx.paging.PagingData
import com.example.socialmedia.domain.models.Post
import com.example.socialmedia.feature_post.domian.repository.PostRepository
import com.example.socialmedia.util.Constants
import com.example.socialmedia.util.Resource
import kotlinx.coroutines.flow.Flow


class GetPostsForFollowsUseCase(
    private val repository: PostRepository
) {
    suspend operator fun invoke(
        page: Int,
        pageSize: Int = Constants.DEFAULT_PAGE_SIZE
    ): Resource<List<Post>> {
        return repository.getPostsForFollows(
            page = page,
            pageSize = pageSize
        )
    }
}