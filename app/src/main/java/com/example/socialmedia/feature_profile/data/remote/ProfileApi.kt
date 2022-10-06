package com.example.socialmedia.feature_profile.data.remote

import com.example.socialmedia.data.dto.response.BasicApiResponse
import com.example.socialmedia.feature_profile.data.remote.response.ProfileResponse
import com.example.socialmedia.feature_profile.data.remote.response.SkillDto
import okhttp3.MultipartBody
import retrofit2.http.GET
import retrofit2.http.Multipart
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

    companion object {
        const val BASE_URL = "http://192.168.0.100:8081/"
    }
}