package com.example.socialmedia.feature_post.presentation.create_post

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.socialmedia.R
import com.example.socialmedia.presentation.components.StandardButton
import com.example.socialmedia.presentation.components.StandardTextField
import com.example.socialmedia.presentation.components.StandardTopBar
import com.example.socialmedia.presentation.util.Screen
import com.example.socialmedia.presentation.util.states.StandardTextFieldState
import com.google.accompanist.systemuicontroller.SystemUiController
import com.google.accompanist.systemuicontroller.rememberSystemUiController


@Composable
fun CreatePostScreen(
    navController: NavController,
    viewModel: CreatePostViewModel = hiltViewModel()
) {

    val systemUiController: SystemUiController = rememberSystemUiController()
    LaunchedEffect(key1 = true) {
        systemUiController.setStatusBarColor(
            color = Color.Gray
        )
    }
    BackHandler {
        navController.navigate(Screen.MainFeedScreen.route)
    }
    Column(modifier = Modifier.fillMaxSize()) {
        StandardTopBar(
            title = {
                Text(
                    text = "Create Post",
                    fontWeight = FontWeight.Bold
                )
            },
            modifier = Modifier.fillMaxWidth(),
            showBackArrow = true,
            onNavigateIconClick = {
                navController.navigate(Screen.MainFeedScreen.route)
            }
        )

        Spacer(modifier = Modifier.height(10.dp))
        Box(
            modifier = Modifier
                .aspectRatio(16f / 9f)
                .padding(horizontal = 34.dp)
                .fillMaxWidth()
                .border(
                    width = 1.dp,
                    Color.Black,
                    shape = RoundedCornerShape(10.dp)
                )
                .clickable {

                },
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = stringResource(id = R.string.add_post),
                tint = Color.Black
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        StandardTextField(
            text = viewModel.description.value.text,
            onValueChange = {
                viewModel.setDescriptionText(
                        StandardTextFieldState(
                            text = it
                        )
                    )
            },
            singleLine = false,
            maxLine = 3,
            modifier = Modifier
                .fillMaxWidth(),
            hint = stringResource(id = R.string.description)

        )
        Spacer(modifier = Modifier.height(15.dp))
        StandardButton(
            modifier = Modifier
                .fillMaxWidth(),
            text = stringResource(id = R.string.post),
            backgroundColor = Color.LightGray,
            contentColor = Color.White,
            textColor = Color.White,
            borderColor = Color.Black,
            onClick = {

            }
        )
    }
}