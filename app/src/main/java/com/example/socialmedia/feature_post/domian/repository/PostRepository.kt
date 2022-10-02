package com.example.socialmedia.feature_post.domian.repository

import android.net.Uri
import androidx.paging.PagingData
import com.example.socialmedia.domain.models.Post
import com.example.socialmedia.util.SimpleResource
import kotlinx.coroutines.flow.Flow
import java.io.File


interface PostRepository {

    val posts: Flow<PagingData<Post>>

    suspend fun createPost(
        description: String,
        imageUri: Uri
    ): SimpleResource
}