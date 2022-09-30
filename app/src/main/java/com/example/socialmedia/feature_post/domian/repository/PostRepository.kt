package com.example.socialmedia.feature_post.domian.repository

import androidx.paging.PagingData
import com.example.socialmedia.domain.models.Post
import kotlinx.coroutines.flow.Flow


interface PostRepository {

    val posts: Flow<PagingData<Post>>
}