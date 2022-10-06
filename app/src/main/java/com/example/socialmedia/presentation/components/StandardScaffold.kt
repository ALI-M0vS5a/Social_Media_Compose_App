package com.example.socialmedia.presentation.components


import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Message
import androidx.compose.material.icons.outlined.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.socialmedia.R
import com.example.socialmedia.domain.models.BottomNavItem
import com.example.socialmedia.presentation.util.Screen
import com.google.accompanist.systemuicontroller.SystemUiController
import com.google.accompanist.systemuicontroller.rememberSystemUiController


@Composable
fun StandardScaffold(
    modifier: Modifier = Modifier,
    navController: NavController,
    showBottomBar: Boolean = true,
    onFabClick: () -> Unit = {},
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
            route = "-"
        ),
        BottomNavItem(
            route = Screen.SearchScreen.route,
            icon = Icons.Default.Favorite,
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
            if (showBottomBar) {
                BottomNavigation(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(82.88.dp),
                    backgroundColor = Color.White,
                    contentColor = Color.Transparent
                ) {
                    bottomNavItem.forEachIndexed { _, bottomNavItem ->
                        StandardButtonNavItem(
                            icon = bottomNavItem.icon,
                            contentDescription = bottomNavItem.contentDescription,
                            selected =navController.currentDestination?.route?.startsWith(bottomNavItem.route) == true,
                            alertCount = bottomNavItem.alertCount,
                            enabled = bottomNavItem.icon != null
                        ) {
                            if (navController.currentDestination?.route != bottomNavItem.route) {
                                navController.navigate(bottomNavItem.route)
                            }
                        }
                    }
                }
            }
        },
        floatingActionButton = {
            if (showBottomBar) {
                FloatingActionButton(
                    onClick = onFabClick,
                    shape = RoundedCornerShape(40),
                    backgroundColor = Color.Black,
                    modifier = Modifier
                        .size(
                            width = 60.26.dp,
                            height = 61.97.dp
                        )
                        .rotate(40f)
                        .offset(
                            x = 10.dp,
                            y = 10.dp
                        )
                ) {
                    Box(
                        modifier = Modifier
                            .border(
                                width = 1.dp,
                                color = Color.White,
                                shape = CircleShape
                            )
                            .size(20.dp)


                    ) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = stringResource(id = R.string.add),
                            tint = Color.White,
                            modifier = Modifier
                                .rotate(50f)
                        )
                    }
                }
            }
        },
        isFloatingActionButtonDocked = true,
        floatingActionButtonPosition = FabPosition.Center,
        modifier = modifier
    ) {
        content(it)
    }

}