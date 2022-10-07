package com.example.socialmedia.feature_activity.data.remote.dto

import com.example.socialmedia.util.Constants
import retrofit2.http.GET
import retrofit2.http.Query


interface ActivityApi {

    @GET("/api/activity/get")
    suspend fun getActivities(
        @Query("page") page: Int = 0,
        @Query("pageSize") pageSize: Int = Constants.DEFAULT_PAGE_SIZE
    ): List<ActivityDto>
}