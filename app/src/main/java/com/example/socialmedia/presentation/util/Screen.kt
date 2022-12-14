package com.example.socialmedia.presentation.util


sealed class Screen(val route: String) {
    object OnBoardingScreen : Screen("onBoarding_screen")
    object LoginScreen : Screen("login_screen")
    object RegisterScreen : Screen("register_screen")
    object MainFeedScreen : Screen("main_feed_screen")
    object ChatScreen : Screen("chat_screen")
    object MessageScreen : Screen("message_screen")
    object ProfileScreen : Screen("profile_screen")
    object CreatePostScreen : Screen("create_post_screen")
    object PostDetailScreen : Screen("post_detail_screen")
    object ActivityScreen : Screen("activity_screen")
    object EditProfileScreen : Screen("edit_profile_screen")
    object SearchScreen : Screen("search_screen")
    object PersonListScreen : Screen("person_list_screen")

}