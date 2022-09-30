package com.example.socialmedia.feature_post.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.socialmedia.domain.models.Post
import com.example.socialmedia.feature_post.data.data_source.remote.PostApi
import com.example.socialmedia.feature_post.data.paging.PostSource
import com.example.socialmedia.feature_post.domian.repository.PostRepository
import com.example.socialmedia.util.Constants
import kotlinx.coroutines.flow.Flow


class PostRepositoryImpl(
    private val api: PostApi
): PostRepository {
    override val posts: Flow<PagingData<Post>>
        get() = Pager(PagingConfig(pageSize = Constants.PAGE_SIZE_POSTS)) {
            PostSource(api)
        }.flow
}