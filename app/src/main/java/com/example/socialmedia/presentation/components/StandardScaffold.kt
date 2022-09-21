package com.example.socialmedia.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.BottomAppBar
import androidx.compose.material.BottomNavigation
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.example.socialmedia.R
import com.example.socialmedia.domain.models.BottomNavItem
import com.example.socialmedia.presentation.util.Screen


@Composable
fun StandardScaffold(
    modifier: Modifier = Modifier,
    navController: NavController,
    showBottomBar: Boolean = false,
    bottomNavItem: List<BottomNavItem> = listOf(
        BottomNavItem(
            route = Screen.MainFeedScreen.route,
            icon = Icons.Outlined.Home,
            contentDescription = stringResource(id = R.string.home),
            alertCount = 50
        ),
        BottomNavItem(
            route = Screen.ChatScreen.route,
            icon = Icons.Outlined.Message,
            contentDescription = stringResource(id = R.string.chat)
        ),
        BottomNavItem(
            route = Screen.FavoriteScreen.route,
            icon = Icons.Outlined.Favorite,
            contentDescription = stringResource(id = R.string.favorite)
        ),
        BottomNavItem(
            route = Screen.ProfileScreen.route,
            icon = Icons.Outlined.Person,
            contentDescription = stringResource(id = R.string.person)
        )
    ),
    content: @Composable (PaddingValues) -> Unit

) {
    Scaffold(
        bottomBar = {
            if(showBottomBar) {
                BottomAppBar(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            shape = CircleShape,
                            color = Color.Black
                        ),
                    backgroundColor = Color.White,
                    cutoutShape = CircleShape
                ) {
                    BottomNavigation(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(
                                shape = CircleShape,
                                color = Color.Black
                            ),
                        backgroundColor = Color.White,
                        contentColor = Color.Transparent
                    ) {
                        bottomNavItem.forEachIndexed { _, bottomNavItem ->
                            StandardButtonNavItem(
                                icon = bottomNavItem.icon,
                                contentDescription = bottomNavItem.contentDescription,
                                selected = bottomNavItem.route == navController.currentDestination?.route,
                                alertCount = bottomNavItem.alertCount,
                            ) {
                                navController.navigate(bottomNavItem.route)
                            }
                        }
                    }
                }
            }
        },
        modifier = modifier
    ) {
        content(it)
    }

}