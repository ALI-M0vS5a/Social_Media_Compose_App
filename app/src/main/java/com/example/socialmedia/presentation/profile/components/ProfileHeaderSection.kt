package com.example.socialmedia.presentation.profile.components


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.socialmedia.R
import com.example.socialmedia.domain.models.User

@Composable
fun ProfileHeaderSection(
    user: User,
    modifier: Modifier = Modifier,
    isOwnProfile: Boolean = true,
    onProfileNameClick: () -> Unit = {}

) {
    Column(
        modifier = modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = modifier
                .size(101.dp)
                .clip(CircleShape)
                .background(
                    color = Color(android.graphics.Color.parseColor("#8CDCE1"))
                )
        ) {
            Image(
                painter = painterResource(id = R.drawable.profile_pic),
                contentDescription = stringResource(id = R.string.profile_pic),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .clip(CircleShape)
                    .size(95.dp)
                    .align(Alignment.Center)
            )
        }
        Spacer(modifier = Modifier.height(18.dp))
        Text(
            text = user.username,
            fontSize = 20.sp,
            fontWeight = FontWeight(700),
            lineHeight = 30.sp,
            color = Color.Black,
            modifier = Modifier
                .clickable {
                    if(isOwnProfile) {
                        onProfileNameClick()
                    }
                }
        )
        Text(
            text = stringResource(id = R.string.profile_name),
            fontSize = 12.sp,
            fontWeight = FontWeight(400),
            lineHeight = 18.sp,
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(45.dp))
        ProfileStats(
            user = user,
            isOwnProfile = isOwnProfile
        )
    }
}