package com.example.socialmedia.feature_activity.presentation


import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.socialmedia.feature_activity.domain.use_case.GetActivitiesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class ActivityViewModel @Inject constructor(
    getActivities: GetActivitiesUseCase
) : ViewModel() {

    val activities = getActivities().cachedIn(viewModelScope)

    private val _uiState = mutableStateOf(ActivityState())
    val uiState: State<ActivityState> = _uiState

    fun onEvent(event: ActivityEvent) {
        when(event) {
            is ActivityEvent.ClickedOnUser -> {

            }

            is ActivityEvent.ClickedOnParent -> {

            }
        }
    }
}
