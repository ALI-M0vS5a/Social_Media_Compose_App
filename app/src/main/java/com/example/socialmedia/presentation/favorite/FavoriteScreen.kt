package com.example.socialmedia.presentation.favorite

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.example.socialmedia.presentation.util.Screen

@Composable
fun FavoriteScreen(
    navController: NavController
) {
    BackHandler {
        navController.navigate(Screen.MainFeedScreen.route)
    }
    Box(modifier = Modifier.fillMaxSize()){
        Text(
            text = "Favorite Screen",
            modifier = Modifier
                .align(Alignment.Center),
            color = Color.Black
        )
    }
}