package com.amazingapps.appquizmvvm.feature_quiz.presentation.utils

sealed class Screen(val route: String) {
    object MainScreen: Screen("main_screen")
    object MoreInfoScreen: Screen("more_info_screen")
    object PlayScreen: Screen("play_screen")
}