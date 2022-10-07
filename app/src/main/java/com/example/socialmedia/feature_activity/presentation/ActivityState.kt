package com.example.socialmedia.feature_activity.presentation

import com.example.socialmedia.domain.models.Activity


data class ActivityState(
    val activities: List<Activity> = emptyList(),
    val isLoading: Boolean = false
)
