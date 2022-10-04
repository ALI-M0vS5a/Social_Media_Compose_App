package com.example.socialmedia.feature_profile.data.remote

import com.example.socialmedia.data.dto.response.BasicApiResponse
import com.example.socialmedia.feature_profile.data.remote.response.ProfileResponse
import retrofit2.http.GET
import retrofit2.http.Query


interface ProfileApi {

    @GET("/api/user/profile")
    suspend fun getProfile(
        @Query("userId") userId: String
    ): BasicApiResponse<ProfileResponse>

    companion object {
        const val BASE_URL = "http://192.168.0.104:8081/"
    }
}