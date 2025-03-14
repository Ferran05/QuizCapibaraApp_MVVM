package com.amazingapps.appquizmvvm.feature_quiz.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.amazingapps.appquizmvvm.feature_quiz.presentation.mainpage.MainScreen
import com.amazingapps.appquizmvvm.feature_quiz.presentation.moreInfo.MoreInfoScreen
import com.amazingapps.appquizmvvm.feature_quiz.presentation.play.PlayScreen
import com.amazingapps.appquizmvvm.feature_quiz.presentation.utils.Screen
import com.amazingapps.appquizmvvm.ui.theme.AppQuizMVVMTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppQuizMVVMTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = Screen.MainScreen.route
                ) {
                    composable(route = Screen.MainScreen.route) {
                        MainScreen(navController)
                    }
                    composable(route = Screen.MoreInfoScreen.route){
                        MoreInfoScreen(navController)
                    }
                    composable(route = Screen.PlayScreen.route) {
                        PlayScreen(navController)
                    }
                }
            }
        }
    }
}