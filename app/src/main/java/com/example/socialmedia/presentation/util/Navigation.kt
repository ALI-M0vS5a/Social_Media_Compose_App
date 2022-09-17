package com.example.socialmedia.presentation.util

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.socialmedia.presentation.login.LoginScreen
import com.example.socialmedia.presentation.onboarding.OnBoardingScreen
import com.google.accompanist.pager.ExperimentalPagerApi

@ExperimentalPagerApi
@Composable
fun Navigation() {
    val navController = rememberNavController()
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
            LoginScreen()
        }
    }
}