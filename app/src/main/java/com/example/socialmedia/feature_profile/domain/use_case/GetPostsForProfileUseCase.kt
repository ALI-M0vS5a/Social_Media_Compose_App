package com.example.socialmedia.feature_profile.domain.use_case

import androidx.paging.PagingData
import com.example.socialmedia.domain.models.Post
import com.example.socialmedia.feature_profile.domain.repository.ProfileRepository
import com.example.socialmedia.util.Resource
import kotlinx.coroutines.flow.Flow


class GetPostsForProfileUseCase(
    private val repository: ProfileRepository
) {
    suspend operator fun invoke(userId: String, page: Int): Resource<List<Post>> {
        return repository.getPostsPaged(
            userId = userId,
            page = page
        )
    }
}