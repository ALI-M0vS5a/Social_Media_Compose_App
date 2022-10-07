package com.example.socialmedia.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.ModeComment
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.socialmedia.R
import com.example.socialmedia.domain.models.Post

@Composable
fun Post(
    modifier: Modifier = Modifier,
    post: Post,
    showProfileImage: Boolean,
    onPostClick: () -> Unit = {}

) {
    val context = LocalContext.current
    Box(
        modifier = modifier
    ) {
        Image(
            painter = rememberAsyncImagePainter(
                model = ImageRequest.Builder(context)
                    .data(post.imageUrl)
                    .crossfade(true)
                    .build()
            ),
            contentDescription = stringResource(id = R.string.post),
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(26.dp)),
            contentScale = ContentScale.Crop
        )
        if(showProfileImage){
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        color = Color.Transparent
                    )
                    .clickable {
                        onPostClick()
                    }
            ) {
                TopPostSection(
                    modifier = Modifier
                        .padding(15.dp),
                    post = post,
                    showProfileImage = showProfileImage
                )
                EngagementsButtonPostSection(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(bottom = 15.dp),
                    onLikeClick = { isLiked ->

                    },
                    onCommentClick = {

                    },
                    onShareClick = {

                    },
                    post = post
                )
            }
        }

    }
}

@Composable
fun TopPostSection(
    modifier: Modifier = Modifier,
    post: Post,
    showProfileImage: Boolean
) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        ProfileImageAndTitle(
            onUsernameClick = { username ->

            },
            post = post,
            showProfileImage = showProfileImage
        )
        Icon(
            imageVector = Icons.Filled.MoreVert,
            contentDescription = stringResource(id = R.string.more_vert),
            tint = Color.White
        )
    }
}

@Composable
fun ProfileImageAndTitle(
    modifier: Modifier = Modifier,
    onUsernameClick: (String) -> Unit = {},
    showProfileImage: Boolean,
    post: Post
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape)
                .background(
                    color = Color(android.graphics.Color.parseColor("#8CDCE1"))
                )
        ) {
            if(showProfileImage){
                Image(
                    painter = painterResource(id = R.drawable.profile_pic),
                    contentDescription = stringResource(id = R.string.profile_pic),
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(42.dp)
                        .align(Alignment.Center)

                )
            }
        }
        Spacer(modifier = Modifier.width(9.dp))
        Column(
            modifier = Modifier,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = post.username,
                fontWeight = FontWeight(400),
                lineHeight = 18.sp,
                fontSize = 12.sp,
                color = Color.White,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .clickable {
                        onUsernameClick("")
                    }
            )
            Spacer(modifier = Modifier.height(1.dp))
            Text(
                text = stringResource(id = R.string.profile_pic_time),
                fontWeight = FontWeight(400),
                lineHeight = 15.sp,
                fontSize = 10.sp,
                color = Color.White,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Composable
fun EngagementsButtonPostSection(
    modifier: Modifier = Modifier,
    isLiked: Boolean = false,
    onLikeClick: (Boolean) -> Unit = {},
    onCommentClick: () -> Unit = {},
    onShareClick: () -> Unit = {},
    post: Post
) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Box(
            modifier = Modifier
                .width(78.dp)
                .height(27.dp)
                .clip(CircleShape)
                .background(
                    color = Color.White
                )
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.Center),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Icon(
                    imageVector = Icons.Filled.Favorite,
                    contentDescription = if (isLiked) {
                        stringResource(id = R.string.unlike)
                    } else {
                        stringResource(id = R.string.like)
                    },
                    modifier = Modifier
                        .size(14.dp)
                        .clickable {
                            onLikeClick(!isLiked)
                        },
                    tint = if(isLiked){
                        Color.Red
                    } else {
                        Color.Black
                    }
                )
                Text(
                    text = stringResource(
                        id = R.string.like_count,
                        post.likeCount
                    ),
                    fontWeight = FontWeight(400),
                    fontSize = 12.sp,
                    lineHeight = 18.sp,
                    color = Color.Black
                )
            }
        }
        Box(
            modifier = Modifier
                .width(78.dp)
                .height(27.dp)
                .clip(CircleShape)
                .background(
                    color = Color.White
                )
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.Center),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Icon(
                    imageVector = Icons.Filled.ModeComment,
                    contentDescription = stringResource(id = R.string.comment),
                    modifier = Modifier
                        .size(14.dp)
                        .clickable {
                            onCommentClick()
                        },
                    tint = Color.Black
                )
                Text(
                    text = stringResource(
                        id = R.string.comment_count,
                        post.commentCount
                    ),
                    fontWeight = FontWeight(400),
                    fontSize = 12.sp,
                    lineHeight = 18.sp,
                    color = Color.Black
                )
            }

        }

        Box(
            modifier = Modifier
                .width(78.dp)
                .height(27.dp)
                .clip(CircleShape)
                .background(
                    color = Color.White
                )
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.Center),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Icon(
                    imageVector = Icons.Filled.Bookmark,
                    contentDescription = stringResource(id = R.string.share),
                    modifier = Modifier
                        .size(14.dp)
                        .clickable {
                            onShareClick()
                        },
                    tint = Color.Black
                )
                Text(
                    text = "5.23k",
                    fontWeight = FontWeight(400),
                    fontSize = 12.sp,
                    lineHeight = 18.sp,
                    color = Color.Black
                )
            }

        }
    }
}

//@Preview
//@Composable
//fun PostPreview() {
//    Post()
//}