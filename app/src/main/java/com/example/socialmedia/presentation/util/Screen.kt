package com.example.socialmedia.presentation.util


sealed class Screen(val route: String) {
    object OnBoardingScreen : Screen("onBoarding_screen")
    object LoginScreen : Screen("login_screen")
}