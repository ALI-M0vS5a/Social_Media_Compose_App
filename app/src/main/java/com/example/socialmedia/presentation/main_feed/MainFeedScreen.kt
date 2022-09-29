package com.example.socialmedia.presentation.main_feed

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.socialmedia.R
import com.example.socialmedia.presentation.components.Post
import com.example.socialmedia.presentation.components.StandardCenteredTopBar
import com.example.socialmedia.presentation.util.Screen
import com.google.accompanist.systemuicontroller.SystemUiController
import com.google.accompanist.systemuicontroller.rememberSystemUiController


@ExperimentalMaterial3Api
@Composable
fun MainFeedScreen(
    navController: NavController,
    finish: () -> Unit
) {

    val systemUiController: SystemUiController = rememberSystemUiController()
    LaunchedEffect(key1 = true) {
        systemUiController.setStatusBarColor(
            color = Color.Gray
        )
    }
    BackHandler {
        finish()
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Spacer(modifier = Modifier.height(10.dp))
        StandardCenteredTopBar(
            navController = navController,
            title = {
                Text(
                    text = stringResource(id = R.string.main_feed_title),
                    fontWeight = FontWeight(250),
                    lineHeight = 27.sp,
                    fontSize = 18.sp,
                    color = Color.Black

                )
            },
            navActions = {
                IconButton(
                    onClick = {
                        navController.navigate(Screen.ActivityScreen.route)
                    }
                ) {
                    Icon(
                        imageVector = Icons.Filled.Notifications,
                        contentDescription = stringResource(id = R.string.activity),
                        tint = Color.Black
                    )
                }
            },
            backgroundColor = Color.White,
            modifier = Modifier
                .fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(10.dp))
        Post(
            post = com.example.socialmedia.domain.models.Post(
                username = "Monica Gamage",
                likeCount = 523,
                commentCount = 523
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(303.dp)
                .padding(horizontal = 41.dp)
        ) {
            navController.navigate(Screen.PostDetailScreen.route)
        }
    }
}

