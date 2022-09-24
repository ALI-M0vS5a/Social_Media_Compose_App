package com.example.socialmedia.presentation.search

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.socialmedia.R
import com.example.socialmedia.domain.models.User
import com.example.socialmedia.presentation.components.StandardTextField
import com.example.socialmedia.presentation.components.StandardTopBar
import com.example.socialmedia.presentation.components.UserProfileItem
import com.example.socialmedia.presentation.util.Screen

@ExperimentalMaterial3Api
@Composable
fun SearchScreen(
    navController: NavController,
    viewModel: SearchViewModel = hiltViewModel()
) {
    BackHandler {
        navController.navigate(Screen.MainFeedScreen.route)
    }
    val state = viewModel.uiState.value
    Column(
        modifier = Modifier
            .fillMaxSize()
    ){
        StandardTopBar(
            modifier = Modifier
                .fillMaxWidth(),
            showBackArrow = true,
            backgroundColor = Color.White,
            onNavigateIconClick = {
                navController.popBackStack()
            },
            navActions = {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = stringResource(id = R.string.menu),
                    tint = Color.Black
                )
            },
            navigationIconTint = Color.Black,
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                text = stringResource(id = R.string.search),
                fontWeight = FontWeight(700),
                fontSize = 24.sp,
                lineHeight = 36.sp,
                color = Color.Black,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(34.dp))
            StandardTextField(
                text = state.search,
                onValueChange = {
                    viewModel.setSearchText(
                        search = it
                    )
                },
                hint = stringResource(id = R.string.search),
                leadingIcon = Icons.Outlined.Search,
                modifier = Modifier
                    .fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(41.dp))
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(bottom = 155.dp),
            ) {
                items(20){
                    UserProfileItem(
                        user = User(
                            username = "Monica Gamage",
                            description = "This is my profile bio This is my profile bio This is my profile bio ",
                            followerCount = 20,
                            followingCount = 200,
                            postCount = 20,
                            profilePictureUrl = ""
                        ),
                        actionIcon = {
                            Icon(
                                imageVector = Icons.Default.Person,
                                contentDescription = null
                            )
                        }
                    )
                }
            }
        }
    }
}