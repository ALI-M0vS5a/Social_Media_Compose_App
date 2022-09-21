package com.example.socialmedia.presentation.main_feed

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.socialmedia.R
import com.example.socialmedia.presentation.components.Post
import com.example.socialmedia.presentation.util.Screen

@Composable
fun MainFeedScreen(
    navController: NavController
) {
    BackHandler {
        navController.navigate(Screen.LoginScreen.route)
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Spacer(modifier = Modifier.height(55.dp))
        TopSection()
        Spacer(modifier = Modifier.height(10.dp))
        Post()

    }
}

@Composable
fun TopSection(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(
                start = 170.dp,
                end = 20.dp
            )
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = stringResource(id = R.string.main_feed_title),
                fontWeight = FontWeight(250),
                lineHeight = 27.sp,
                fontSize = 18.sp,
                color = Color.Black
            )
            Icon(
                imageVector = Icons.Filled.Notifications,
                contentDescription = stringResource(id = R.string.activity),
                tint = Color.Black
            )
        }
    }
}

@Composable
fun StatusLazyRow(
    modifier: Modifier = Modifier
) {

}
