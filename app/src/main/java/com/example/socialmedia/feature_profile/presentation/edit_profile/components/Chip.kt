package com.example.socialmedia.feature_profile.presentation.edit_profile.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ChipBorder
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun Chip(
    modifier: Modifier = Modifier,
    text: String,
    selected: Boolean = false,
    selectedColor: Color = Color(android.graphics.Color.parseColor("#8CDCE1")),
    unSelectedColor: Color = Color.Black,
    onChipClick: () -> Unit
) {
    Text(
        text = text,
        color = if(selected) selectedColor else unSelectedColor,
        modifier = modifier
            .clip(RoundedCornerShape(50.dp))
            .border(
                width = 1.dp,
                color = if(selected) selectedColor else unSelectedColor,
                shape = RoundedCornerShape(50.dp)
            )
            .padding(8.dp)
            .clickable {
                onChipClick()
            }

    )
}