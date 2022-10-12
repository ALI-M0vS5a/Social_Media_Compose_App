package com.example.socialmedia.presentation.components


import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.socialmedia.R


@Composable
fun StandardTopBar(
    modifier: Modifier = Modifier,
    showBackArrow: Boolean = false,
    backgroundColor: Color = Color.Gray,
    navigationIconTint: Color = Color.White,
    onNavigateIconClick: () -> Unit = {},
    navActions: @Composable RowScope.() -> Unit = {},
    title: @Composable () -> Unit = {}
) {
    TopAppBar(
        title = title,
        modifier = modifier
            .width(100.dp),
        navigationIcon = {
            if (showBackArrow) {
                IconButton(
                    onClick = {
                        onNavigateIconClick()
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = stringResource(id = R.string.arrow_back),
                        tint = navigationIconTint
                    )
                }
            }
        },
        actions = {
            navActions()
        },
        backgroundColor = backgroundColor,
        elevation = 0.dp
    )
}
