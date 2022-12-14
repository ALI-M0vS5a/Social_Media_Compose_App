package com.example.socialmedia.feature_post.domian.repository

import android.net.Uri
import androidx.paging.PagingData
import com.example.socialmedia.domain.models.Comment
import com.example.socialmedia.domain.models.Post
import com.example.socialmedia.domain.models.UserItem
import com.example.socialmedia.util.Resource
import com.example.socialmedia.util.SimpleResource
import kotlinx.coroutines.flow.Flow
import java.io.File


interface PostRepository {

    suspend fun getPostsForFollows(page: Int, pageSize: Int): Resource<List<Post>>

    suspend fun createPost(
        description: String,
        imageUri: Uri
    ): SimpleResource

    suspend fun getPostDetails(postId: String): Resource<Post>

    suspend fun getCommentsForPost(postId: String): Resource<List<Comment>>

    suspend fun createComment(postId: String, comment: String): SimpleResource

    suspend fun likeParent(parentId: String, parentType: Int): SimpleResource

    suspend fun unlikeParent(parentId: String, parentType: Int): SimpleResource

    suspend fun getLikesForParent(parentId: String): Resource<List<UserItem>>
}