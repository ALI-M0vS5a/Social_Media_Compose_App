package com.example.socialmedia.presentation.components



import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun StandardButton(
    modifier: Modifier = Modifier,
    text: String = "",
    backgroundColor: Color = Color(android.graphics.Color.parseColor("#8CDCE1")),
    contentColor: Color = Color.White,
    textColor: Color = Color.White,
    borderColor: Color = Color.White,
    onClick: () -> Unit = {},
) {
    OutlinedButton(
        border = BorderStroke(
            width = 1.dp,
            color = borderColor
        ),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = backgroundColor,
            contentColor = contentColor
        ),
        shape = CircleShape,
        modifier = modifier
            .fillMaxWidth()
            .height(52.dp)
            .padding(horizontal = 30.dp),
        onClick = {
            onClick()
        }) {
        Text(
            text = text,
            fontWeight = FontWeight(700),
            fontSize = 16.sp,
            letterSpacing = 0.6.sp,
            lineHeight = 24.sp,
            color = textColor
        )
    }
}