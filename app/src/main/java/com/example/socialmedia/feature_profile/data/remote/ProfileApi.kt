package com.example.socialmedia.feature_profile.data.remote

import com.example.socialmedia.data.dto.response.BasicApiResponse
import com.example.socialmedia.data.dto.response.UserItemDto
import com.example.socialmedia.feature_profile.data.remote.request.FollowUpdateRequest
import com.example.socialmedia.feature_profile.data.remote.response.ProfileResponse
import com.example.socialmedia.feature_profile.data.remote.response.SkillDto
import com.example.socialmedia.util.Resource
import okhttp3.MultipartBody
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Part
import retrofit2.http.Query


interface ProfileApi {

    @GET("/api/user/profile")
    suspend fun getProfile(
        @Query("userId") userId: String
    ): BasicApiResponse<ProfileResponse>

    @GET("/api/skills/get")
    suspend fun getSkills(): List<SkillDto>

    @Multipart
    @PUT("/api/user/update")
    suspend fun updateProfile(
        @Part bannerImage: MultipartBody.Part?,
        @Part profilePicture: MultipartBody.Part?,
        @Part updateProfileData: MultipartBody.Part
    ): BasicApiResponse<Unit>

    @GET("/api/user/search")
    suspend fun searchUser(
        @Query("query") query: String
    ): List<UserItemDto>

    @POST("/api/following/follow")
    suspend fun followUser(
        @Body request: FollowUpdateRequest
    ): BasicApiResponse<Unit>

    @DELETE("/api/following/unfollow")
    suspend fun unfollowUser(
        @Query("userId") userId: String
    ): BasicApiResponse<Unit>

}