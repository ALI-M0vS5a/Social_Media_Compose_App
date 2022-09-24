package com.example.socialmedia.presentation.profile

import androidx.activity.compose.BackHandler
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ExperimentalMotionApi
import androidx.navigation.NavController
import com.example.socialmedia.domain.models.Post
import com.example.socialmedia.domain.models.User
import com.example.socialmedia.presentation.components.Post
import com.example.socialmedia.presentation.profile.components.MotionLayoutProfileHeader
import com.example.socialmedia.presentation.profile.components.ProfileHeaderSection
import com.example.socialmedia.presentation.util.Screen

@ExperimentalMotionApi
@Composable
fun ProfileScreen(
    navController: NavController
) {
    BackHandler {
        navController.navigate(Screen.MainFeedScreen.route)
    }
    val state = rememberLazyGridState()

    val firstOffset = remember { derivedStateOf { state.firstVisibleItemScrollOffset } }
    val progress by animateFloatAsState(
        targetValue = if (firstOffset.value in 0..4) 0f else 1f,
        animationSpec = if (firstOffset.value in 0..4)
            tween(700) else tween(300)
    )
    Column(
        modifier = Modifier
            .fillMaxSize()

    ) {

        MotionLayoutProfileHeader(
            user = User(
                username = "Monica Gomage",
                profilePictureUrl = "",
                description = "",
                followingCount = 200,
                followerCount = 200,
                postCount = 24
            ),
            isOwnProfile = true,
            progress = progress,
            navController = navController
        )


        LazyVerticalGrid(
            columns = GridCells.Adaptive(110.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            contentPadding = PaddingValues(bottom = 175.dp),
            state = state
        ) {
            item(
                span = { GridItemSpan(110) }
            ) {
                ProfileHeaderSection(
                    user = User(
                        profilePictureUrl = "",
                        username = "Monica Gamage",
                        description = "",
                        followerCount = 200,
                        followingCount = 300,
                        postCount = 20
                    ),
                    onEditButtonClick = {
                        navController.navigate(Screen.EditProfileScreen.route)
                    }
                )
            }
            item(
                span = { GridItemSpan(110) }
            ) {
                Spacer(modifier = Modifier.height(25.dp))
            }
            items(20) {
                Post(
                    post = Post(
                        username = "Monica Gamage",
                        likeCount = 523,
                        commentCount = 523
                    ),
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


