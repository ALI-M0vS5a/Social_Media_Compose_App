package com.example.socialmedia.presentation.profile.components


import android.graphics.Color
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ExperimentalMotionApi
import androidx.constraintlayout.compose.MotionLayout
import androidx.constraintlayout.compose.MotionScene
import androidx.constraintlayout.compose.layoutId
import androidx.navigation.NavController
import com.example.socialmedia.R
import com.example.socialmedia.domain.models.User
import com.example.socialmedia.presentation.util.Screen

@ExperimentalMotionApi
@Composable
fun MotionLayoutProfileHeader(
    modifier: Modifier = Modifier,
    navController: NavController,
    user: User,
    isOwnProfile: Boolean,
    progress: Float
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
                navController.navigate(Screen.MainFeedScreen.route)
            },
            modifier = Modifier
                .layoutId("nav_back")
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = null
            )
        }
        Image(
            painter = painterResource(id = R.drawable.profile_pic),
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