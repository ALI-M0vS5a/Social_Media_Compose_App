package com.example.socialmedia.feature_post.presentation.post_detail.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
import com.example.socialmedia.domain.models.Comment
import com.example.socialmedia.feature_post.presentation.post_detail.PostDetailEvent
import com.example.socialmedia.feature_post.presentation.post_detail.PostDetailViewModel

@Composable
fun CommentItem(
    modifier: Modifier = Modifier,
    comment: Comment,
    onLikeClick: () -> Unit,
    viewModel: PostDetailViewModel = hiltViewModel(),
    isLiked: Boolean = false
) {
    Card(
        modifier = modifier
            .clip(RoundedCornerShape(10)),
        colors = CardDefaults.cardColors(
            containerColor = Color(android.graphics.Color.parseColor("#F6F7F9"))
        ),

        ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.Start
        ) {
            Row {
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
                                .data(comment.profileImageUrl)
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
                Column {
                    Text(
                        text = comment.username,
                        fontWeight = FontWeight(700),
                        fontSize = 19.2.sp,
                        color = Color(android.graphics.Color.parseColor("#242424")),
                        lineHeight = 24.sp
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = comment.comment,
                        fontWeight = FontWeight(400),
                        fontSize = 14.sp,
                        color = Color(android.graphics.Color.parseColor("#242424")),
                        lineHeight = 21.sp
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = comment.formattedTime,
                        fontWeight = FontWeight(400),
                        fontSize = 16.sp,
                        color = Color(android.graphics.Color.parseColor("#242424")),
                        lineHeight = 24.sp
                    )
                }
                IconButton(onClick = { onLikeClick()}) {
                    Icon(
                        imageVector = Icons.Outlined.FavoriteBorder,
                        contentDescription = stringResource(id = R.string.favorite),
                        tint = if(isLiked) {
                            Color.Red
                        } else {
                            Color.White
                        },

                    )
                }
            }
        }
    }
}