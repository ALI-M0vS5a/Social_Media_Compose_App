package com.example.socialmedia.presentation.components


import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
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
import com.example.socialmedia.presentation.ui.theme.Shapes

@ExperimentalMaterial3Api
@Composable
fun UserProfileItem(
    modifier: Modifier = Modifier,
    user: User,
    actionIcon: @Composable () -> Unit = {},
    onItemClick: () -> Unit = {},
    onActionItemClick: () -> Unit = {}
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(10.dp)
            .wrapContentHeight(),
        onClick = onItemClick,
        shape = RoundedCornerShape(25.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 5.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = Color.LightGray
        ),

    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.profile_pic),
                contentDescription = stringResource(id = R.string.profile_pic),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(75.dp)
                    .clip(CircleShape)

            )
            Spacer(modifier = Modifier.width(10.dp))
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(0.8f),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = user.username,
                    fontWeight = FontWeight(700),
                    fontSize = 16.sp,
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(3.dp))
                Text(
                    text = user.description,
                    fontWeight = FontWeight(300),
                    fontSize = 14.sp,
                    color = Color.White
                )
            }
            IconButton(
                modifier = Modifier,
                onClick = {
                    onActionItemClick()
                }
            ) {
                actionIcon()
            }
        }

    }
}