package com.example.socialmedia.feature_profile.presentation.person_list

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PersonAdd
import androidx.compose.material.icons.filled.PersonRemove
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.socialmedia.R
import com.example.socialmedia.feature_profile.presentation.person_list.components.UserLikedByItem
import com.example.socialmedia.presentation.components.StandardTopBar
import com.example.socialmedia.presentation.util.Screen
import com.example.socialmedia.util.UiEvent
import kotlinx.coroutines.flow.collectLatest

@ExperimentalMaterial3Api
@Composable
fun PersonListScreen(
    onNavigate: (String) -> Unit = {},
    onNavigatePopBackStack: () -> Unit = {},
    viewModel: PersonListViewModel = hiltViewModel()
) {
    val state = viewModel.uiState.value
    val context = LocalContext.current
    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is UiEvent.Message -> {
                    Toast.makeText(
                        context,
                        event.uiText.asString(context),
                        Toast.LENGTH_SHORT
                    ).show()
                }
                else -> Unit
            }
        }
    }
    Column(
        modifier = Modifier.fillMaxSize()
    ) {

        StandardTopBar(
            modifier = Modifier
                .fillMaxWidth(),
            showBackArrow = true,
            onNavigateIconClick = {

            },
            title = {
                Text(
                    text = stringResource(id = R.string.person_list_title_top_bar)
                )
            },
        )
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                items(state.users) { user ->
                    UserLikedByItem(
                        user = user,
                        onToggleFollowClick = {
                            viewModel.onEvent(PersonListEvent.ToggleFollowStateForUser(user.userId))
                        },
                        imageVector = if (user.isFollowing) {
                            Icons.Default.PersonRemove
                        } else {
                            Icons.Default.PersonAdd
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(94.dp),
                        onItemClick = {
                            onNavigate(Screen.ProfileScreen.route + "?userId=${user.userId}")
                        },
                        ownUserId = state.ownUserId
                    )
                }
            }
            if (state.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    }
}