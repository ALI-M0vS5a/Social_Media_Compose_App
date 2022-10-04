package com.example.socialmedia.feature_profile.presentation.profile.components


import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ExperimentalMotionApi
import com.example.socialmedia.R
import com.example.socialmedia.domain.models.User

@ExperimentalMotionApi
@Composable
fun ProfileHeaderSection(
    user: User,
    modifier: Modifier = Modifier,
    isOwnProfile: Boolean = true,
    onEditButtonClick: () -> Unit = {}
) {
    Column(
        modifier = modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(155.dp))
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
            isOwnProfile = isOwnProfile,
            onEditButtonClick = {
                onEditButtonClick()
            }
        )
    }
}