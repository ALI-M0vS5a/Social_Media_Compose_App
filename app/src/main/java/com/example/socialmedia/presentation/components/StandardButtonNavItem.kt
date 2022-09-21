package com.example.socialmedia.presentation.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
@Throws(IllegalArgumentException::class)
fun RowScope.StandardButtonNavItem(
    modifier: Modifier = Modifier,
    icon: ImageVector? = null,
    contentDescription: String? = null,
    alertCount: Int? = null,
    selected: Boolean = false,
    selectedColor: Color = Color(android.graphics.Color.parseColor("#7DB9B3")),
    unselectedColor: Color = Color.Black,
    enabled: Boolean = true,
    onClick: () -> Unit
) {
    if (alertCount != null && alertCount < 0) {
        throw IllegalArgumentException("Alert count can't be null")
    }
    val lineLength = animateFloatAsState(
        targetValue = if(selected) 1f else 0f,
        animationSpec = tween(
            durationMillis = 800
        )
    )
    BottomNavigationItem(
        selected = selected,
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        selectedContentColor = selectedColor,
        unselectedContentColor = unselectedColor,
        icon = {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(22.dp)
                    .drawBehind {
                        if (selected) {
                            drawLine(
                                color = if (selected) selectedColor else unselectedColor,
                                start = Offset(size.width / 2f - lineLength.value * 15.dp.toPx(), size.height),
                                end = Offset(size.width / 2f + lineLength.value * 15.dp.toPx(), size.height),
                                cap = StrokeCap.Round
                            )
                        }
                    }
            ) {
                if (icon != null) {
                    Icon(
                        imageVector = icon,
                        contentDescription = contentDescription,
                        modifier = Modifier
                            .align(Alignment.TopCenter),
                        tint = if(selected) selectedColor else unselectedColor
                    )
                }
                if (alertCount != null) {
                    val alertText = if (alertCount > 99) {
                        "99+"
                    } else {
                        alertCount.toString()
                    }
                    Text(
                        text = alertText,
                        fontSize = 10.sp,
                        modifier = Modifier
                            .align(Alignment.TopCenter)
                            .offset(
                                x = 10.dp,
                                y = (-7).dp
                            )
                            .size(15.dp)
                            .clip(CircleShape)
                            .background(
                                color = selectedColor
                            ),
                        textAlign = TextAlign.Center,
                        color = Color.Black

                    )
                }
            }
        }
    )
}