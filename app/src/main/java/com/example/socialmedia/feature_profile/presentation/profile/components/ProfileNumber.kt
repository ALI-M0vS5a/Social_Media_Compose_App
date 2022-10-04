package com.example.socialmedia.feature_profile.presentation.profile.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ProfileNumber(
    number: Int,
    text: String
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = text,
            fontWeight = FontWeight(400),
            fontSize = 12.sp,
            lineHeight = 18.sp,
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(7.dp))
        Text(
            text = number.toString(),
            fontWeight = FontWeight(700),
            fontSize = 16.sp,
            lineHeight = 24.sp,
            color = Color.Black
        )
    }
}