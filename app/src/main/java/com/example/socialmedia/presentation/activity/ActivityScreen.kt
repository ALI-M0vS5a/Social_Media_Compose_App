package com.example.socialmedia.presentation.activity

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.socialmedia.R
import com.example.socialmedia.domain.models.Activity
import com.example.socialmedia.domain.util.ActivityAction
import com.example.socialmedia.domain.util.DateFormatUtil
import com.example.socialmedia.presentation.activity.components.ActivityItem
import com.example.socialmedia.presentation.components.StandardTopBar
import kotlin.random.Random


@Composable
fun ActivityScreen(
    navController: NavController,
    viewModel: ActivityViewModel = hiltViewModel()
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxWidth()) {
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
                    navController.popBackStack()
                }
            )
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
            ){
                items(20){
                    ActivityItem(
                        activity = Activity(
                            username = "Monica Gamage",
                            actionType = if(Random.nextInt(2) == 0){
                                ActivityAction.LikedPost
                            } else ActivityAction.CommentedOnPost,
                            formattedTime = DateFormatUtil.timestampToFormattedString(
                                timestamp = System.currentTimeMillis(),
                                pattern = "MMM dd, HH:mm"
                            )
                        ),
                        modifier = Modifier
                            .padding(10.dp)
                    )
                }
            }
        }
    }
}