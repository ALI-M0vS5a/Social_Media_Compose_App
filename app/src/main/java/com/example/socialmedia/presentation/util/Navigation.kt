package com.example.socialmedia.presentation.util

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.socialmedia.presentation.activity.ActivityScreen
import com.example.socialmedia.presentation.chat.ChatScreen
import com.example.socialmedia.presentation.create_post.CreatePostScreen
import com.example.socialmedia.presentation.favorite.FavoriteScreen
import com.example.socialmedia.presentation.login.LoginScreen
import com.example.socialmedia.presentation.main_feed.MainFeedScreen
import com.example.socialmedia.presentation.onboarding.OnBoardingScreen
import com.example.socialmedia.presentation.post_detail.PostDetailScreen
import com.example.socialmedia.presentation.profile.ProfileScreen
import com.example.socialmedia.presentation.register.RegisterScreen
import com.google.accompanist.pager.ExperimentalPagerApi

@ExperimentalMaterial3Api
@ExperimentalPagerApi
@Composable
fun Navigation(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Screen.OnBoardingScreen.route
    ){
        composable(route = Screen.OnBoardingScreen.route){
            OnBoardingScreen(
                navController = navController
            )
        }
        composable(route = Screen.LoginScreen.route){
            LoginScreen(
                navController = navController
            )
        }
        composable(route = Screen.RegisterScreen.route){
            RegisterScreen(
                navController = navController
            )
        }
        composable(route = Screen.MainFeedScreen.route){
            MainFeedScreen(
                navController = navController
            )
        }
        composable(route = Screen.ChatScreen.route){
            ChatScreen(
                navController = navController
            )
        }
        composable(route = Screen.FavoriteScreen.route){
            FavoriteScreen(
                navController = navController
            )
        }
        composable(route = Screen.ProfileScreen.route){
            ProfileScreen(
                navController = navController
            )
        }
        composable(route = Screen.CreatePostScreen.route){
            CreatePostScreen(
                navController = navController
            )
        }
        composable(route = Screen.PostDetailScreen.route){
            PostDetailScreen(
                navController = navController
            )
        }
        composable(route = Screen.ActivityScreen.route){
            ActivityScreen(
                navController = navController
            )
        }
    }
}