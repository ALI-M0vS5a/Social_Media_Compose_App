package com.example.socialmedia.domain.models

import com.example.socialmedia.domain.util.ActivityType


data class Activity(
    val userId: String,
    val parentId: String,
    val username: String,
    val actionType: ActivityType,
    val formattedTime: String
)
