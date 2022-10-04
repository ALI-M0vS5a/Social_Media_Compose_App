package com.example.socialmedia.feature_post.presentation.main_feed

import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.example.socialmedia.R
import com.example.socialmedia.domain.models.Post
import com.example.socialmedia.presentation.components.Post
import com.example.socialmedia.presentation.components.StandardCenteredTopBar
import com.example.socialmedia.presentation.util.Screen
import com.google.accompanist.systemuicontroller.SystemUiController
import com.google.accompanist.systemuicontroller.rememberSystemUiController


@ExperimentalMaterial3Api
@Composable
fun MainFeedScreen(
    onNavigate: (String) -> Unit = {},
    onNavigatePopBackStack: () -> Unit = {},
    finish: () -> Unit,
    viewModel: MainFeedViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val state = viewModel.state.value
    val posts = viewModel.posts.collectAsLazyPagingItems()
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
            onNavigatePopBackStack = onNavigatePopBackStack,
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
                        onNavigate(Screen.ActivityScreen.route)
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
        Box(modifier = Modifier.fillMaxSize()) {
            if (state.isLoadingFirstTime) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }
            LazyColumn {
                items(posts) { post ->
                    Post(
                        post = Post(
                            username = post?.username ?: "",
                            imageUrl = post?.imageUrl,
                            profilePictureUrl = post?.profilePictureUrl,
                            description = post?.description,
                            likeCount = post?.likeCount ?: 0,
                            commentCount = post?.commentCount ?: 0
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(303.dp)
                            .padding(horizontal = 41.dp)
                    ) {
                      onNavigate(Screen.PostDetailScreen.route)
                    }
                }
                item {
                    if (state.isLoadingNewPosts) {
                        CircularProgressIndicator(
                            modifier = Modifier.align(Alignment.BottomCenter)
                        )
                    }
                }
                posts.apply {
                    when {
                        loadState.refresh is LoadState.Loading -> {
                            viewModel.onEvents(MainFeedEvents.LoadedPage)
                        }
                        loadState.append is LoadState.Loading -> {
                            viewModel.onEvents(MainFeedEvents.LoadMorePosts)
                        }
                        loadState.append is LoadState.NotLoading -> {
                            viewModel.onEvents(MainFeedEvents.LoadedPage)
                        }
                        loadState.append is LoadState.Error -> {
                            Toast.makeText(
                                context,
                                "Error",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
            }
        }
    }
}

