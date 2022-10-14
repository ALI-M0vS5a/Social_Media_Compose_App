package com.example.socialmedia.feature_profile.presentation.profile

import android.util.Base64
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ExperimentalMotionApi
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.socialmedia.R
import com.example.socialmedia.domain.models.User
import com.example.socialmedia.feature_post.presentation.util.PostEvent
import com.example.socialmedia.feature_profile.presentation.profile.components.MotionLayoutProfileHeader
import com.example.socialmedia.feature_profile.presentation.profile.components.ProfileHeaderSection
import com.example.socialmedia.presentation.components.Post
import com.example.socialmedia.presentation.util.Screen
import com.example.socialmedia.util.UiEvent
import kotlinx.coroutines.flow.collectLatest

@ExperimentalMotionApi
@Composable
fun ProfileScreen(
    onNavigate: (String) -> Unit = {},
    onNavigatePopBackStack: () -> Unit = {},
    viewModel: ProfileViewModel = hiltViewModel(),
    userId: String? = null,
    onLogout: () -> Unit = {},
) {
    BackHandler {
        onNavigate(Screen.MainFeedScreen.route)
    }
    val pagingState = viewModel.pagingState.value
    val profileState = viewModel.state.value
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
            when (event) {
                is UiEvent.Message -> {
                    Toast.makeText(
                        context,
                        event.uiText.asString(context),
                        Toast.LENGTH_SHORT
                    ).show()
                }
                is PostEvent.OnLiked -> {

                }
            }
        }
    }
    Box(modifier = Modifier.fillMaxSize()) {
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
                    isOwnProfile = profile.isOwnProfile,
                    progress = progress,
                    onNavigate = onNavigate,
                    onLogoutClick = {
                        viewModel.onEvent(ProfileEvent.ShowLogoutDialog)
                    },
                    onChatClick = {
                        onNavigate(
                            Screen.MessageScreen.route + "/${profile.userId}/${profile.username}/${
                                Base64.encodeToString(
                                    profile.profilePictureUrl.encodeToByteArray(),
                                    0
                                )
                            }"
                        )
                    }
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
                            isOwnProfile = profile.isOwnProfile,
                            isFollowing = profile.isFollowing
                        )
                    }
                    item(
                        span = { GridItemSpan(110) }
                    ) {
                        Spacer(modifier = Modifier.height(25.dp))
                    }

                    items(pagingState.items.size) { i ->
                        val post = pagingState.items[i]
                        if (i > pagingState.items.size - 1 && !pagingState.endReached && !pagingState.isLoading) {
                            viewModel.loadNextPosts()
                        }
                        Post(
                            post = post,
                            showProfileImage = false,
                            modifier = Modifier
                                .padding(8.dp)
                                .aspectRatio(1f)
                                .clip(RoundedCornerShape(5.dp)),
                            onPostClick = {
                                onNavigate(Screen.PostDetailScreen.route + "/${post.id}")
                            },
                            onShareClick = {

                            },
                            onCommentClick = {

                            },
                            onLikeClick = {

                            }
                        )
                    }
                }
            }
        }
        if (profileState.isLogoutDialogVisible) {
            AlertDialog(
                onDismissRequest = {
                    viewModel.onEvent(ProfileEvent.DismissLogoutDialog)
                },
                title = {
                    Text(
                        text = stringResource(id = R.string.do_you_want_to_logout),
                        fontSize = 24.sp,
                        modifier = Modifier
                            .fillMaxWidth(),
                        textAlign = TextAlign.Center,
                    )
                },
                buttons = {
                    Spacer(modifier = Modifier.height(50.dp))
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        OutlinedButton(
                            onClick = {
                                viewModel.onEvent(ProfileEvent.Logout)
                                viewModel.onEvent(ProfileEvent.DismissLogoutDialog)
                                onLogout()
                            },
                            modifier = Modifier
                                .fillMaxWidth(),
                            border = BorderStroke(
                                width = 0.1.dp,
                                color = Color.LightGray
                            )
                        ) {
                            Text(
                                text = stringResource(id = R.string.logout),
                                color = Color(android.graphics.Color.parseColor("#8CDCE1"))
                            )
                        }
                        OutlinedButton(
                            onClick = {
                                viewModel.onEvent(ProfileEvent.DismissLogoutDialog)
                            },
                            modifier = Modifier
                                .fillMaxWidth(),
                            border = BorderStroke(
                                width = 0.1.dp,
                                color = Color.LightGray
                            )
                        ) {
                            Text(
                                text = stringResource(id = R.string.Cancel),
                                color = Color.White
                            )
                        }
                    }
                },
                shape = RoundedCornerShape(25.dp),
                modifier = Modifier
                    .requiredHeight(240.dp)
            )
        }
    }

}



