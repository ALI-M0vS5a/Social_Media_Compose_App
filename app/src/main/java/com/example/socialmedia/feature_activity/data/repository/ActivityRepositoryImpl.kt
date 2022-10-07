package com.example.socialmedia.feature_activity.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.socialmedia.domain.models.Activity
import com.example.socialmedia.feature_activity.data.paging.ActivitySource
import com.example.socialmedia.feature_activity.data.remote.dto.ActivityApi
import com.example.socialmedia.feature_activity.domain.repository.ActivityRepository
import com.example.socialmedia.util.Constants
import kotlinx.coroutines.flow.Flow


class ActivityRepositoryImpl(
    private val api: ActivityApi
) : ActivityRepository {
    override val activities: Flow<PagingData<Activity>>
        get() = Pager(PagingConfig(pageSize = Constants.DEFAULT_PAGE_SIZE)) {
            ActivitySource(api)
        }.flow
}