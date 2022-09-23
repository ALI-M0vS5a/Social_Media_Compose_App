package com.example.socialmedia.presentation.create_post

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.socialmedia.presentation.components.StandardTopBar
import com.example.socialmedia.presentation.util.Screen
import com.google.accompanist.systemuicontroller.SystemUiController
import com.google.accompanist.systemuicontroller.rememberSystemUiController


@Composable
fun CreatePostScreen(
    navController: NavController
) {

    val systemUiController: SystemUiController = rememberSystemUiController()
    LaunchedEffect(key1 = true){
        systemUiController.setStatusBarColor(
            color = Color.Gray)
    }
    BackHandler {
        navController.navigate(Screen.MainFeedScreen.route)
    }
//    Box(
//        modifier = Modifier
//            .fillMaxSize()
//            .background(
//                color = Color.White
//            )
//    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
//            Spacer(modifier = Modifier.height(44.9.dp))
            StandardTopBar(
                title = {
                    Text(
                        text = "Create Post",
                        fontWeight = FontWeight.Bold
                    )
                },
                modifier = Modifier.fillMaxWidth(),
                showBackArrow = true,
                navActions = {
                    IconButton(
                        onClick = {

                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = null
                        )
                    }
                },
                onNavigateIconClick = {
                    navController.navigate(Screen.MainFeedScreen.route)
                }
            )
        }
    }
//}