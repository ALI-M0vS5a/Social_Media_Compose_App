package com.example.socialmedia.domain.models

import com.example.socialmedia.domain.util.ActivityAction


data class Activity(
    val username: String,
    val actionType: ActivityAction,
    val formattedTime: String
)
