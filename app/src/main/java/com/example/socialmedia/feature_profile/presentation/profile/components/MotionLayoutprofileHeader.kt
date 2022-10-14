package com.example.socialmedia.feature_profile.presentation.profile.components


import android.graphics.Color
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ExperimentalMotionApi
import androidx.constraintlayout.compose.MotionLayout
import androidx.constraintlayout.compose.MotionScene
import androidx.constraintlayout.compose.layoutId
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.socialmedia.R
import com.example.socialmedia.domain.models.User
import com.example.socialmedia.presentation.util.Screen

@ExperimentalMotionApi
@Composable
fun MotionLayoutProfileHeader(
    modifier: Modifier = Modifier,
    onNavigate: (String) -> Unit = {},
    onNavigatePopBackStack: () -> Unit = {},
    user: User,
    isOwnProfile: Boolean,
    onLogoutClick: () -> Unit = {},
    progress: Float,
    onChatClick: () -> Unit = {}
) {
    val context = LocalContext.current
    val motionScene = remember {
        context.resources.openRawResource(R.raw.motion_scene)
            .readBytes()
            .decodeToString()
    }
    MotionLayout(
        motionScene = MotionScene(content = motionScene),
        progress = progress,
        modifier = modifier
            .fillMaxWidth()
    ) {
        val properties = motionProperties(id = "box")
        Box(
            modifier = Modifier
                .layoutId("box")
                .background(
                    color = properties.value.color("background")
                )
                .fillMaxWidth()
                .height(200.dp)
        )
        IconButton(
            onClick = {
                onNavigate(Screen.MainFeedScreen.route)
            },
            modifier = Modifier
                .layoutId("nav_back")
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = stringResource(id = R.string.arrow_back)
            )
        }
        if(isOwnProfile) {
            IconButton(
                onClick = onLogoutClick,
                modifier = Modifier
                    .layoutId("logoutOrChat")
            ) {
                Icon(
                    imageVector = Icons.Default.Logout,
                    contentDescription = stringResource(id = R.string.logout)
                )
            }
        } else {
            IconButton(
                onClick = onChatClick,
                modifier = Modifier
                    .layoutId("logoutOrChat")
            ) {
                Icon(
                    imageVector = Icons.Default.Chat,
                    contentDescription = stringResource(id = R.string.chat)
                )
            }
        }
        Image(
            painter = rememberAsyncImagePainter(
                model = ImageRequest.Builder(context)
                    .data(user.profilePictureUrl)
                    .crossfade(true)
                    .build()
            ),
            contentDescription = stringResource(id = R.string.profile_pic),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .layoutId("profile_pic")
                .clip(CircleShape)
                .border(
                    width = 2.dp,
                    color = androidx.compose.ui.graphics.Color(Color.parseColor("#8CDCE1")),
                    shape = CircleShape
                )

        )
        Spacer(modifier = Modifier.height(18.dp))
        Text(
            text = user.username,
            fontSize = 20.sp,
            fontWeight = FontWeight(700),
            lineHeight = 30.sp,
            color = androidx.compose.ui.graphics.Color.Black,
            modifier = Modifier
                .layoutId("username")

        )

    }
}