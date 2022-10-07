package com.example.socialmedia.feature_activity.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.example.socialmedia.R
import com.example.socialmedia.domain.models.Activity
import com.example.socialmedia.feature_activity.presentation.components.ActivityItem
import com.example.socialmedia.presentation.components.StandardTopBar


@Composable
fun ActivityScreen(
    onNavigate: (String) -> Unit = {},
    onNavigatePopBackStack: () -> Unit = {},
    viewModel: ActivityViewModel = hiltViewModel()
) {
    val state = viewModel.uiState.value
    val activities = viewModel.activities.collectAsLazyPagingItems()
    LaunchedEffect(key1 = true) {
        println("activity: ${activities.itemCount}")
    }
    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {
            StandardTopBar(
                navActions = {
                    IconButton(
                        onClick = {

                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Settings,
                            contentDescription = stringResource(id = R.string.activity_settings),
                            tint = Color.Black
                        )
                    }
                },
                backgroundColor = Color.White,
                title = {
                    Text(
                        text = stringResource(id = R.string.activity_title),
                        fontWeight = FontWeight(700),
                        color = Color.Black,
                        lineHeight = 28.8.sp,
                        fontSize = 24.sp
                    )
                },
                modifier = Modifier
                    .fillMaxWidth(),
                navigationIconTint = Color.Black,
                showBackArrow = true,
                onNavigateIconClick = {
                    onNavigatePopBackStack()
                }
            )
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
            ){
                items(activities){ activity ->
                    activity?.let {
                        ActivityItem(
                            activity = Activity(
                                userId = activity.userId,
                                username = activity.username,
                                parentId = activity.parentId,
                                actionType = activity.actionType,
                                formattedTime = activity.formattedTime
                            )
                        )
                    }
                }
            }
        }
        if(state.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier
                    .align(Alignment.Center)
            )
        }
    }
}