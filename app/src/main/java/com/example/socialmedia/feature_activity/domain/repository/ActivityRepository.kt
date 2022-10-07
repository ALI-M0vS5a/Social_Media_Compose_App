package com.example.socialmedia.feature_activity.domain.repository

import androidx.paging.PagingData
import com.example.socialmedia.domain.models.Activity
import kotlinx.coroutines.flow.Flow


interface ActivityRepository {
    val activities: Flow<PagingData<Activity>>
}