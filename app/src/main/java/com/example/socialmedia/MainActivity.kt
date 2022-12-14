package com.example.socialmedia


import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.constraintlayout.compose.ExperimentalMotionApi
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavDeepLinkBuilder
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.socialmedia.presentation.components.StandardScaffold
import com.example.socialmedia.presentation.ui.theme.SocialMediaTheme
import com.example.socialmedia.presentation.util.Navigation
import com.example.socialmedia.presentation.util.Screen
import com.example.socialmedia.util.UiEvent
import com.google.accompanist.pager.ExperimentalPagerApi
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@ExperimentalMotionApi
@ExperimentalMaterial3Api
@ExperimentalPagerApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels()
    private var backPressed = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().apply {
            setKeepOnScreenCondition {
                viewModel.isLoading.value
            }
        }
        setContent {
            SocialMediaTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val navController = rememberNavController()
                    val navBackStackEntry by navController.currentBackStackEntryAsState()

                    LaunchedEffect(key1 = true) {
                        viewModel.eventFlow.collectLatest { events ->
                            when(events) {
                                is UiEvent.Navigate -> {
                                    navController.navigate(Screen.MainFeedScreen.route)

                                }
                                is UiEvent.Message -> {

                                }
                                else -> Unit
                            }
                        }
                    }
                    StandardScaffold(
                        navController = navController,
                        showBottomBar = shouldShowBottomBar(navBackStackEntry),
                        modifier = Modifier.fillMaxSize(),
                        onFabClick = {
                            navController.navigate(Screen.CreatePostScreen.route)
                        }
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(
                                    color = Color.White
                                )
                        ) {
                            Navigation(
                                navController = navController,
                                finish = finish
                            )
                        }
                    }
                }
            }
        }
    }


    private fun shouldShowBottomBar(backStackEntry: NavBackStackEntry?): Boolean {
        val doesRouteMatch = backStackEntry?.destination?.route in listOf(
            Screen.MainFeedScreen.route,
            Screen.ChatScreen.route,
            Screen.SearchScreen.route,
        )
        val isOwnProfile = backStackEntry?.destination?.route == "${Screen.ProfileScreen.route}?userId={userId}" &&
                backStackEntry.arguments?.getString("userId") == null

        return doesRouteMatch || isOwnProfile
    }

    private val finish: () -> Unit = {
        if(backPressed + 3000 > System.currentTimeMillis()) {
            finish()
        } else {
            Toast.makeText(
                this,
                "Press again to exit",
                Toast.LENGTH_SHORT
            ).show()
        }
        backPressed = System.currentTimeMillis()
    }
}
