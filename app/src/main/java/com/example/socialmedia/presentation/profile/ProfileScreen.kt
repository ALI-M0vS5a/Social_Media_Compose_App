package com.example.socialmedia.presentation.profile

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.socialmedia.domain.models.Post
import com.example.socialmedia.domain.models.User
import com.example.socialmedia.presentation.components.Post
import com.example.socialmedia.presentation.components.StandardTopBar
import com.example.socialmedia.presentation.profile.components.ProfileHeaderSection
import com.example.socialmedia.presentation.util.Screen

@Composable
fun ProfileScreen(
    navController: NavController
) {
    BackHandler {
        navController.navigate(Screen.MainFeedScreen.route)
    }
    Column(modifier = Modifier.fillMaxSize()) {
        StandardTopBar(
            showBackArrow = true,
            backgroundColor = Color.White,
            navigationIconTint = Color.Black,
            onNavigateIconClick = {
                navController.navigate(Screen.MainFeedScreen.route)
            }
        )
        LazyVerticalGrid(
            columns = GridCells.Adaptive(110.dp),
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(bottom = 175.dp)
        ) {
            item(
                span = { GridItemSpan(110) }
            ) {
                Spacer(modifier = Modifier.height(34.dp))
                ProfileHeaderSection(
                    user = User(
                        profilePictureUrl = "",
                        username = "Monica Gamage",
                        description = "",
                        followerCount = 200,
                        followingCount = 300,
                        postCount = 20
                    )
                )
            }
            item(
                span = { GridItemSpan(110) }
            ) {
                Spacer(modifier = Modifier.height(25.dp))
            }
            items(20){
                Post(
                    post = Post(
                        username = "Monica Gamage",
                        likeCount = 523,
                        commentCount = 523
                    ) ,
                    showProfileImage = false,
                    modifier = Modifier
                        .padding(8.dp)
                        .aspectRatio(1f)
                        .clip(RoundedCornerShape(5.dp))
                )
            }

        }
    }
}


