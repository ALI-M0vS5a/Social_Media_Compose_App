package com.example.socialmedia.feature_profile.presentation.profile.components

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.socialmedia.R
import com.example.socialmedia.domain.models.User
import com.example.socialmedia.presentation.components.StandardButton


@Composable
fun ProfileStats(
    user: User,
    modifier: Modifier = Modifier,
    isFollowing: Boolean = true,
    isOwnProfile: Boolean = true,
    onFollowClick: () -> Unit = {},
    onEditButtonClick: () -> Unit = {}
) {
    Column(
        modifier = modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            ProfileNumber(
                number = user.postCount,
                text = stringResource(id = R.string.profile_posts)
            )
            ProfileNumber(
                number = user.followerCount,
                text = stringResource(id = R.string.profile_followers)
            )
            ProfileNumber(
                number = user.followingCount,
                text = stringResource(id = R.string.profile_follower)
            )
        }
        if (!isOwnProfile) {
            Spacer(modifier = Modifier.height(10.dp))
            StandardButton(
                borderColor = Color.Black,
                backgroundColor = if (isFollowing) {
                    Color.Red
                } else {
                    Color.White
                },
                textColor = if (isFollowing) {
                    Color.White
                } else Color.Black,
                contentColor = Color.Black,
                onClick = {
                    onFollowClick()
                },
                text = if (isFollowing) {
                    stringResource(id = R.string.unfollow)
                } else {
                    stringResource(id = R.string.follow)
                }
            )
        } else {
            Spacer(modifier = Modifier.height(10.dp))
            StandardButton(
                borderColor = Color.Black,
                backgroundColor = Color.LightGray,
                textColor = Color.White,
                contentColor = Color.Black,
                onClick = {
                    onEditButtonClick()
                },
                text = stringResource(id = R.string.edit_profile)
            )
        }
    }
}