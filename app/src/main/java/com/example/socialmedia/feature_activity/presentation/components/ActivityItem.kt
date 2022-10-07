package com.example.socialmedia.feature_activity.presentation.components


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp
import com.example.socialmedia.R
import com.example.socialmedia.domain.models.Activity
import com.example.socialmedia.domain.util.ActivityType


@Composable
fun ActivityItem(
    modifier: Modifier = Modifier,
    activity: Activity
) {
    Card(
        modifier = modifier
            .clip(RoundedCornerShape(10)),
        colors = CardDefaults.cardColors(
            containerColor = Color(android.graphics.Color.parseColor("#F1F1FE"))
        ),
    ) {
        Row(
            modifier = modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            val filterText = when(activity.actionType) {
                is ActivityType.LikedPost -> {
                    stringResource(id = R.string.liked)
                }
                is ActivityType.CommentedOnPost -> {
                    stringResource(id = R.string.commented_on)
                }
                is ActivityType.FollowedUser -> {
                    stringResource(id = R.string.followed_you)
                }
                is ActivityType.LikedComment -> {
                    stringResource(id = R.string.liked)
                }
            }
            val actionText = when(activity.actionType) {
                is ActivityType.LikedPost -> {
                    stringResource(id = R.string.your_post)
                }
                is ActivityType.CommentedOnPost -> {
                    stringResource(id = R.string.your_post)
                }
                is ActivityType.FollowedUser -> ""

                is ActivityType.LikedComment -> {
                    stringResource(id = R.string.your_comment)
                }
            }
            Text(
                text = buildAnnotatedString {
                    val boldStyle = SpanStyle(fontWeight = FontWeight.Bold)
                    withStyle(boldStyle) {
                        append(activity.username)
                    }
                    append(" $filterText ")
                    withStyle(boldStyle){
                        append(actionText)
                    }
                },
                fontSize = 13.sp
            )
            Text(
                text = activity.formattedTime,
                fontSize = 13.sp

            )
        }
    }
}