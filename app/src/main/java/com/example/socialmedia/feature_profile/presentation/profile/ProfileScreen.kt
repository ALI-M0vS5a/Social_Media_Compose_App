package com.example.socialmedia.feature_profile.presentation.profile

import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ExperimentalMotionApi
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.socialmedia.domain.models.Post
import com.example.socialmedia.domain.models.User
import com.example.socialmedia.presentation.components.Post
import com.example.socialmedia.feature_profile.presentation.profile.components.MotionLayoutProfileHeader
import com.example.socialmedia.feature_profile.presentation.profile.components.ProfileHeaderSection
import com.example.socialmedia.presentation.util.Screen
import com.example.socialmedia.util.UiEvent
import kotlinx.coroutines.flow.collectLatest

@ExperimentalMotionApi
@Composable
fun ProfileScreen(
    onNavigate: (String) -> Unit = {},
    onNavigatePopBackStack: () -> Unit = {},
    viewModel: ProfileViewModel = hiltViewModel(),
    userId: String
) {
    BackHandler {
        onNavigate(Screen.MainFeedScreen.route)
    }
    val context = LocalContext.current
    val state = rememberLazyGridState()
    val viewModelState = viewModel.state.value
    val firstOffset = remember { derivedStateOf { state.firstVisibleItemScrollOffset } }
    val progress by animateFloatAsState(
        targetValue = if (firstOffset.value in 0..4) 0f else 1f,
        animationSpec = if (firstOffset.value in 0..4)
            tween(700) else tween(300)
    )
    LaunchedEffect(key1 = true) {
        viewModel.getProfile(userId)
        viewModel.eventFlow.collectLatest { event ->
            when(event) {
                is UiEvent.Message -> {
                    Toast.makeText(
                        context,
                        event.uiText.asString(context),
                        Toast.LENGTH_SHORT
                    ).show()
                }
                else -> Unit
            }
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()

    ) {
        viewModelState.profile?.let { profile ->
            MotionLayoutProfileHeader(
                user = User(
                    userId = profile.userId,
                    username = profile.username,
                    profilePictureUrl = profile.profilePictureUrl,
                    bannerUrl = profile.bannerUrl,
                    description = profile.bio,
                    followingCount = profile.followingCount,
                    followerCount = profile.followerCount,
                    postCount = profile.postCount
                ),
                isOwnProfile = true,
                progress = progress,
                onNavigate = onNavigate
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
                            userId = profile.userId,
                            profilePictureUrl = profile.profilePictureUrl,
                            bannerUrl = profile.bannerUrl,
                            username = profile.username,
                            description = profile.bio,
                            followerCount = profile.followerCount,
                            followingCount = profile.followingCount,
                            postCount = profile.postCount
                        ),
                        onEditButtonClick = {
                            onNavigate(Screen.EditProfileScreen.route + "/${profile.userId}")
                        },
                        isOwnProfile = profile.isOwnProfile
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
}



