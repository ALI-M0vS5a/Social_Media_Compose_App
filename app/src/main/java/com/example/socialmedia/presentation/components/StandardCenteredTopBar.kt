package com.example.socialmedia.presentation.components

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.example.socialmedia.R

@ExperimentalMaterial3Api
@Composable
fun StandardCenteredTopBar(
    modifier: Modifier = Modifier,
    title: @Composable () -> Unit = {},
    showBackArrow: Boolean = false,
    navController: NavController,
    navActions: @Composable RowScope.() -> Unit = {},
    backgroundColor: Color = Color.Gray

) {
    CenterAlignedTopAppBar(
        title = title,
        navigationIcon = {
            if(showBackArrow){
                IconButton(
                    onClick = {
                        navController.popBackStack()
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = stringResource(id = R.string.arrow_back)
                    )
                }
            }
        },
        actions = {
           navActions()
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = backgroundColor
        ),
        modifier = modifier
    )
}