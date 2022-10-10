package com.example.socialmedia.feature_profile.presentation.person_list.components


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.socialmedia.R
import com.example.socialmedia.domain.models.UserItem


@ExperimentalMaterial3Api
@Composable
fun UserLikedByItem(
    modifier: Modifier = Modifier,
    user: UserItem,
    onToggleFollowClick: () -> Unit,
    imageVector: ImageVector,
    onItemClick: () -> Unit,
    ownUserId: String = ""
) {
    Card(
        modifier = modifier
            .clip(RoundedCornerShape(10))
            .padding(10.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 20.dp
        ),
        onClick = {
            onItemClick()
        }

    ) {
        Row(
            modifier = modifier,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Spacer(modifier = Modifier.width(22.dp))
            Box(
                modifier = Modifier
                    .size(58.dp)
                    .clip(CircleShape)
                    .background(
                        color = Color(android.graphics.Color.parseColor("#8CDCE1"))
                    )

            ) {
                Image(
                    painter = rememberAsyncImagePainter(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(user.profilePictureUrl)
                            .crossfade(true)
                            .build()
                    ),
                    contentDescription = stringResource(id = R.string.profile_pic),
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(52.dp)
                        .align(Alignment.Center)

                )
            }
            Spacer(modifier = Modifier.width(10.dp))
                Column(
                    modifier = Modifier
                        .weight(1f),
                ) {
                    Text(
                        text = user.username,
                        fontWeight = FontWeight(700),
                        fontSize = 19.2.sp,
                        color = Color(android.graphics.Color.parseColor("#242424")),
                        lineHeight = 24.sp
                    )
                    Text(
                        text = user.bio,
                        fontWeight = FontWeight(400),
                        fontSize = 14.sp,
                        color = Color(android.graphics.Color.parseColor("#242424")),
                        lineHeight = 21.sp
                    )
            }
            if(user.userId != ownUserId){
                IconButton(onClick = {
                    onToggleFollowClick()
                }) {
                    Icon(
                        imageVector = imageVector,
                        contentDescription = null,
                        tint = Color.Black
                    )
                }
            }
        }
    }
}