package com.example.socialmedia.feature_post.presentation.post_detail



import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.socialmedia.R
import com.example.socialmedia.feature_post.presentation.post_detail.components.CommentItem
import com.example.socialmedia.feature_post.presentation.util.showKeyBoard
import com.example.socialmedia.presentation.components.StandardTopBar
import com.example.socialmedia.presentation.util.Screen
import com.example.socialmedia.util.UiEvent
import com.example.socialmedia.util.sendSharedPostIntent
import kotlinx.coroutines.flow.collectLatest


@Composable
fun PostDetailScreen(
    onNavigate: (String) -> Unit = {},
    onNavigatePopBackStack: () -> Unit = {},
    viewModel: PostDetailViewModel = hiltViewModel(),
    shouldShowKeyBoard: Boolean = false
) {
    val state = viewModel.uiState.value
    val context = LocalContext.current
    val focusRequester = remember {
        FocusRequester()
    }
    LaunchedEffect(key1 = true) {
        if(shouldShowKeyBoard) {
            context.showKeyBoard()
            focusRequester.requestFocus()
        }
    }
    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
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
    Box(modifier = Modifier.fillMaxSize()) {

        Column(modifier = Modifier.fillMaxSize()) {
            StandardTopBar(
                navActions = {
                    Row(
                        modifier = Modifier,
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        IconButton(
                            onClick = {
                                viewModel.onEvent(PostDetailEvent.LikePost)
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Default.FavoriteBorder,
                                contentDescription = null,
                                tint = if(state.post?.isLiked == true) {
                                    Color.Red
                                } else {
                                    Color.White
                                }
                            )
                        }
                        IconButton(onClick = { }) {
                            Box(
                                modifier = Modifier
                                    .border(
                                        width = 1.dp,
                                        color = Color.Black,
                                        shape = CircleShape
                                    )
                                    .size(20.dp)


                            ) {
                                Icon(
                                    imageVector = Icons.Default.Add,
                                    contentDescription = stringResource(id = R.string.add),
                                    tint = Color.Black,
                                )
                            }
                        }
                        IconButton(onClick = {
                            context.sendSharedPostIntent(viewModel.uiState.value.post?.id ?: "")
                        }) {
                            Icon(
                                imageVector = Icons.Outlined.FileUpload,
                                contentDescription = stringResource(id = R.string.upload),
                                tint = Color.Black
                            )
                        }
                    }
                },
                showBackArrow = true,
                modifier = Modifier
                    .fillMaxWidth(),
                backgroundColor = Color.White,
                navigationIconTint = Color.Black,
                onNavigateIconClick = {
                    onNavigatePopBackStack()
                }
            )

            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth(),
                contentPadding = PaddingValues(bottom = 100.dp)
            ) {
                item {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {

                        TopPostSection(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 12.dp),
                            onUserNameClick = {
                                onNavigate(Screen.ProfileScreen.route + "?userId=${state.post?.userId}")
                            }
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(250.75.dp)
                        ) {
                            Image(
                                painter = rememberAsyncImagePainter(
                                    model = ImageRequest.Builder(LocalContext.current)
                                        .data(state.post?.imageUrl)
                                        .crossfade(true)
                                        .build()
                                ),
                                contentDescription = stringResource(id = R.string.post_detail_image),
                                contentScale = ContentScale.FillBounds,
                                modifier = Modifier
                                    .fillMaxSize()
                            )
                        }
                        Spacer(modifier = Modifier.height(10.dp))
                        EngagementsBottomPost(
                            modifier = Modifier
                                .fillMaxWidth(),
                            onNavigate = onNavigate,
                            focusRequester = focusRequester
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = state.post?.description ?: "",
                            fontWeight = FontWeight(400),
                            fontSize = 14.sp,
                            lineHeight = 21.sp,
                            color = Color(android.graphics.Color.parseColor("#828282")),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 20.dp)
                        )
                        Spacer(modifier = Modifier.height(16.dp))

                    }
                }
                items(state.comments) { comment ->
                    CommentItem(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                horizontal = 14.dp,
                                vertical = 10.dp
                            )
                            .wrapContentHeight(),
                        comment = comment,
                        onLikeClick = {
                            viewModel.onEvent(PostDetailEvent.LikeComment(comment.commentId))
                        },
                        isLiked = state.comments.find {
                            it.commentId == comment.commentId
                        }?.isLiked == true
                    )
                }
            }
        }
        Row(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .padding(horizontal = 35.dp, vertical = 5.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp)
                    .shadow(
                        elevation = 5.dp,
                        shape = CircleShape
                    )

                    .background(color = Color.White)
            ) {
                OutlinedTextField(
                    value = state.CommentTextField,
                    onValueChange = {
                        viewModel.onEvent(PostDetailEvent.EnteredComment(it))
                    },
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color.White,
                        cursorColor = Color.Black,
                        textColor = Color.Black,
                        focusedIndicatorColor = Color.White,
                        unfocusedIndicatorColor = Color.White
                    ),
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .focusRequester(focusRequester = focusRequester),
                    placeholder = {
                        Text(text = stringResource(id = R.string.add_comment))
                    }
                )
                if (state.isLoadingCreateComment) {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .align(Alignment.CenterEnd)
                            .offset(
                                x = (-30).dp
                            )
                            .size(24.dp),
                        color = Color.Black
                    )
                } else {
                    FloatingActionButton(
                        onClick = {
                            viewModel.onEvent(PostDetailEvent.Comment)
                        },
                        shape = RoundedCornerShape(40),
                        backgroundColor = Color.Black,
                        modifier = Modifier
                            .size(
                                width = 60.26.dp,
                                height = 61.97.dp
                            )
                            .rotate(40f)
                            .offset(
                                x = (-15).dp,
                                y = (12).dp
                            )
                            .align(Alignment.CenterEnd)
                    ) {

                        Icon(
                            imageVector = Icons.Outlined.Send,
                            contentDescription = stringResource(id = R.string.add),
                            tint = if (state.error == null) {
                                Color.White
                            } else {
                                Color.LightGray
                            },
                            modifier = Modifier
                                .rotate(-70f)
                                .align(Alignment.Center)
                                .offset(
                                    x = 4.dp,
                                    y = (-1).dp
                                )
                        )
                    }
                }
            }
        }
    }
}


@Composable
fun TopPostSection(
    modifier: Modifier = Modifier,
    viewModel: PostDetailViewModel = hiltViewModel(),
    onUserNameClick: () -> Unit = {}
) {
    val state = viewModel.uiState.value
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            modifier = Modifier.wrapContentWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(38.dp)
                    .clip(CircleShape)
                    .background(
                        color = Color(android.graphics.Color.parseColor("#8CDCE1"))
                    )
            ) {
                Image(
                    painter = rememberAsyncImagePainter(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(state.post?.profilePictureUrl)
                            .crossfade(true)
                            .build()
                    ),
                    contentDescription = stringResource(id = R.string.profile_pic),
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(32.dp)
                        .align(Alignment.Center)

                )
            }
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                text = state.post?.username ?: "",
                fontWeight = FontWeight(400),
                fontSize = 16.sp,
                color = Color(android.graphics.Color.parseColor("#242424")),
                lineHeight = 24.sp,
                modifier = Modifier
                    .clickable { onUserNameClick() }
            )
        }
    }
}

@Composable
fun EngagementsBottomPost(
    modifier: Modifier = Modifier,
    onNavigate: (String) -> Unit,
    viewModel: PostDetailViewModel = hiltViewModel(),
    focusRequester: FocusRequester = FocusRequester()
) {
    val state = viewModel.uiState.value
    val context = LocalContext.current
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Box(
            modifier = Modifier
                .wrapContentWidth()
                .wrapContentHeight()
        ) {
            Row(
                modifier = Modifier
                    .clickable {
                        context.showKeyBoard()
                        focusRequester.requestFocus()
                    }
            ) {
                Text(
                    text = "20",
                    fontWeight = FontWeight(400),
                    fontSize = 14.sp,
                    lineHeight = 21.sp,
                    color = Color(android.graphics.Color.parseColor("#828282"))
                )
                Spacer(modifier = Modifier.width(5.dp))
                Icon(
                    imageVector = Icons.Outlined.Comment,
                    contentDescription = stringResource(id = R.string.comment),
                    tint = Color.Black
                )
            }
        }
        Box(
            modifier = Modifier
                .wrapContentWidth()
                .wrapContentHeight()
        ) {
            Row {
                Text(
                    text = viewModel.uiState.value.post?.likeCount.toString(),
                    fontWeight = FontWeight(400),
                    fontSize = 14.sp,
                    lineHeight = 21.sp,
                    color = Color(android.graphics.Color.parseColor("#828282")),
                    modifier = Modifier
                        .clickable {
                            onNavigate(Screen.PersonListScreen.route + "/${state.post?.id}")
                        }
                )
                Spacer(modifier = Modifier.width(5.dp))
                Icon(
                    imageVector = Icons.Outlined.FavoriteBorder,
                    contentDescription = stringResource(id = R.string.favorite),
                    tint = Color.Black,
                    modifier = Modifier
                        .clickable {

                        }
                )
            }
        }
    }
}

