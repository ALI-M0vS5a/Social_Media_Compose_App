package com.example.socialmedia.feature_profile.domain.use_case

import com.example.socialmedia.domain.models.UserItem
import com.example.socialmedia.feature_profile.domain.repository.ProfileRepository
import com.example.socialmedia.util.Resource


class SearchUserUseCase(
    private val repository: ProfileRepository
) {
    suspend operator fun invoke(query: String): Resource<List<UserItem>> {
        if(query.isBlank()) {
            return Resource.Success(data = emptyList())
        }
        return repository.searchUser(query)
    }
}