package com.example.socialmedia.presentation.util

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.constraintlayout.compose.ExperimentalMotionApi
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.socialmedia.feature_auth.presentation.login.LoginScreen
import com.example.socialmedia.feature_auth.presentation.register.RegisterScreen
import com.example.socialmedia.feature_post.presentation.create_post.CreatePostScreen
import com.example.socialmedia.feature_post.presentation.main_feed.MainFeedScreen
import com.example.socialmedia.feature_post.presentation.post_detail.PostDetailScreen
import com.example.socialmedia.feature_profile.presentation.edit_profile.EditProfileScreen
import com.example.socialmedia.feature_profile.presentation.profile.ProfileScreen
import com.example.socialmedia.presentation.activity.ActivityScreen
import com.example.socialmedia.presentation.chat.ChatScreen
import com.example.socialmedia.presentation.onboarding.OnBoardingScreen
import com.example.socialmedia.presentation.search.SearchScreen
import com.google.accompanist.pager.ExperimentalPagerApi

@ExperimentalMotionApi
@ExperimentalMaterial3Api
@ExperimentalPagerApi
@Composable
fun Navigation(
    navController: NavHostController,
    finish: () -> Unit
) {
    NavHost(
        navController = navController,
        startDestination = Screen.OnBoardingScreen.route
    ) {
        composable(route = Screen.OnBoardingScreen.route) {
            OnBoardingScreen(
                onNavigate = navController::navigate
            )
        }
        composable(route = Screen.LoginScreen.route) {
            LoginScreen(
                onNavigate = navController::navigate
            )
        }
        composable(route = Screen.RegisterScreen.route) {
            RegisterScreen(
                onNavigate = navController::navigate,
                onNavigatePopBackStack = navController::popBackStack
            )
        }
        composable(route = Screen.MainFeedScreen.route) {
            MainFeedScreen(
                onNavigate = navController::navigate,
                onNavigatePopBackStack = navController::popBackStack,
                finish = {
                    finish()
                }
            )
        }
        composable(route = Screen.ChatScreen.route) {
            ChatScreen(
                onNavigate = navController::navigate
            )
        }
        composable(route = Screen.SearchScreen.route) {
            SearchScreen(
                onNavigate = navController::navigate,
                onNavigatePopBackStack = navController::popBackStack
            )
        }
        composable(
            route = Screen.ProfileScreen.route + "?userId={userId}",
            arguments = listOf(
                navArgument(name = "userId") {
                    type = NavType.StringType
                    nullable = true
                    defaultValue = null
                }
            )
        ) {
            ProfileScreen(
                onNavigate = navController::navigate,
                userId = it.arguments?.getString("userId") ?: ""
            )
        }
        composable(route = Screen.CreatePostScreen.route) {
            CreatePostScreen(
                onNavigate = navController::navigate
            )
        }
        composable(route = Screen.PostDetailScreen.route) {
            PostDetailScreen(
                onNavigatePopBackStack = navController::popBackStack
            )
        }
        composable(route = Screen.ActivityScreen.route) {
            ActivityScreen(
                onNavigatePopBackStack = navController::popBackStack
            )
        }
        composable(
            route = Screen.EditProfileScreen.route + "/{userId}",
            arguments = listOf(
                navArgument(name = "userId") {
                    type = NavType.StringType
                }
            )
        ) {
            EditProfileScreen(
                onNavigate = navController::navigate,
                onNavigatePopBackStack = navController::popBackStack
            )
        }
    }
}