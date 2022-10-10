package com.example.socialmedia.util

import com.example.socialmedia.domain.models.Post


class DefaultPostLiker: PostLiker {
    override suspend fun toggleLike(
        posts: List<Post>,
        parentId: String,
        onRequest: suspend (isLiked: Boolean) -> SimpleResource,
        onStateUpdated: (List<Post>) -> Unit
    ) {
        val posT = posts.find { it.id == parentId}
        val currentlyLiked = posT?.isLiked == true
        val currentLikeCount = posT?.likeCount ?: 0
        val newPosts = posts.map { post ->
            if (post.id == parentId) {
                post.copy(
                    isLiked = !post.isLiked,
                    likeCount = if(currentlyLiked) {
                        post.likeCount - 1
                    } else post.likeCount + 1
                )
            } else post
        }
        onStateUpdated(newPosts)
       when(onRequest(currentlyLiked)) {
           is Resource.Success -> Unit
           is Resource.Error -> {
               val oldPosts = posts.map { post ->
                   if(post.id == parentId) {
                       post.copy(
                           isLiked = currentlyLiked,
                           likeCount = currentLikeCount
                       )
                   } else post
               }
               onStateUpdated(oldPosts)
           }
       }
    }
}