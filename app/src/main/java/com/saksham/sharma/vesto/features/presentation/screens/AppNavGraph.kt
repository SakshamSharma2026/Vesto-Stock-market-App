package com.saksham.sharma.vesto.features.presentation.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.saksham.sharma.vesto.features.presentation.screens.home.HomeScreen
import com.saksham.sharma.vesto.features.presentation.screens.intro.IntroScreen


@Composable
fun AppNavGraph(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    Surface(modifier.fillMaxSize()) {
        NavHost(navController, startDestination = Screen.IntroScreen.route) {
            composable(route = Screen.IntroScreen.route) {
                IntroScreen(modifier, onPrimaryBtnClicked = {
                    navController.navigate(Screen.HomeScreen.route)
                })
            }
            composable(route = Screen.HomeScreen.route) {
                HomeScreen()
            }
        }
    }
}