package com.example.socialmedia.feature_chat.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun RemoteMessage(
    modifier: Modifier = Modifier,
    message: String,
    formattedTime: String,
    color: Color = Color.White,
    triangleWidth: Dp = 30.dp,
    triangleHeight: Dp = 30.dp
) {
    val cornerRadius = MaterialTheme.shapes.medium.bottomStart
    Row(modifier = modifier.fillMaxWidth()) {
        Box(
            modifier = Modifier
                .weight(1f)
                .background(
                    color = Color.LightGray,
                    shape = RoundedCornerShape(10.dp)
                )
                .padding(12.dp)
                .drawBehind {
                    val cornerRadiusPx = cornerRadius.toPx(
                        shapeSize = size,
                        density = Density(density)
                    )
                    val path = Path().apply {
                        moveTo(
                            0f,
                            size.height - cornerRadiusPx
                        )
                        lineTo(0f, size.height + triangleHeight.toPx())
                        lineTo(
                            triangleWidth.toPx(),
                            size.height - cornerRadiusPx
                        )
                        close()
                    }
                    drawPath(
                        path = path,
                        color = Color.LightGray
                    )
                }
        ) {
            Text(
                text = message
            )
        }
        Spacer(modifier = Modifier.width(10.dp))
        Text(
            text = formattedTime,
            color = Color.LightGray,
            modifier = Modifier.align(Alignment.Bottom)
        )
    }
}