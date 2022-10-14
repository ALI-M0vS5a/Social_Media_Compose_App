package com.example.socialmedia.presentation.util

import android.content.Intent
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.constraintlayout.compose.ExperimentalMotionApi
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.socialmedia.feature_auth.presentation.login.LoginScreen
import com.example.socialmedia.feature_auth.presentation.register.RegisterScreen
import com.example.socialmedia.feature_post.presentation.create_post.CreatePostScreen
import com.example.socialmedia.feature_post.presentation.main_feed.MainFeedScreen
import com.example.socialmedia.feature_post.presentation.post_detail.PostDetailScreen
import com.example.socialmedia.feature_profile.presentation.edit_profile.EditProfileScreen
import com.example.socialmedia.feature_profile.presentation.profile.ProfileScreen
import com.example.socialmedia.feature_activity.presentation.ActivityScreen
import com.example.socialmedia.feature_profile.presentation.person_list.PersonListScreen
import com.example.socialmedia.feature_chat.presentation.chat.ChatScreen
import com.example.socialmedia.feature_chat.presentation.message.MessageScreen
import com.example.socialmedia.presentation.onboarding.OnBoardingScreen
import com.example.socialmedia.feature_profile.presentation.search.SearchScreen
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
                onNavigate = navController::navigate,
                onNavigatePopBackStack = navController::popBackStack,
                finish = finish
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
        composable(
            route = Screen.ChatScreen.route
        ) {
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
                userId = it.arguments?.getString("userId"),
                onLogout = {
                    navController.navigate(route = Screen.LoginScreen.route)
                }
            )
        }
        composable(route = Screen.CreatePostScreen.route) {
            CreatePostScreen(
                onNavigate = navController::navigate
            )
        }
        composable(
            route = Screen.PostDetailScreen.route + "/{postId}?shouldShowKeyboard={shouldShowKeyboard}",
            arguments = listOf(
                navArgument(
                    name = "postId"
                ) {
                    type = NavType.StringType
                },
                navArgument(
                    name = "shouldShowKeyBoard"
                ) {
                    type = NavType.BoolType
                    defaultValue = false
                }
            ),
            deepLinks = listOf(
                navDeepLink {
                    action = Intent.ACTION_VIEW
                    uriPattern = "https://montymobile.com/{postId}"
                }
            )
        ) {
            val shouldShowKeyBoard = it.arguments?.getBoolean("shouldShowKeyBoard") ?: false
            PostDetailScreen(
                onNavigate = navController::navigate,
                onNavigatePopBackStack = navController::popBackStack,
                shouldShowKeyBoard = shouldShowKeyBoard
            )
        }
        composable(route = Screen.ActivityScreen.route) {
            ActivityScreen(
                onNavigate = navController::navigate,
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

        composable(
            route = Screen.PersonListScreen.route + "/{parentId}",
            arguments = listOf(
                navArgument(name = "parentId") {
                    type = NavType.StringType
                }
            )
        ) {
            PersonListScreen(
                onNavigate = navController::navigate
            )
        }
        composable(
            route = Screen.MessageScreen.route + "/{remoteUserId}/{remoteUsername}/{remoteUserProfilePictureUrl}?chatId={chatId}",
            arguments = listOf(
                navArgument("chatId") {
                    type = NavType.StringType
                    nullable = true
                },
                navArgument("remoteUserId") {
                    type = NavType.StringType
                },
                navArgument("remoteUsername") {
                    type = NavType.StringType
                },
                navArgument("remoteUserProfilePictureUrl") {
                    type = NavType.StringType
                }
            )
        ) {
            val remoteUsername = it.arguments?.getString("remoteUsername")!!
            val remoteUserProfilePictureUrl = it.arguments?.getString("remoteUserProfilePictureUrl")!!
            MessageScreen(
                remoteUsername = remoteUsername,
                encodedRemoteUserProfilePictureUrl = remoteUserProfilePictureUrl,
                onNavigate = navController::navigate
            )
        }
    }
}