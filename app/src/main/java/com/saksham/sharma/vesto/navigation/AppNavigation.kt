package com.saksham.sharma.vesto.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.core.common.navigation_constant.AuthFeature
import com.core.common.navigation_constant.MainFeature
import com.saksham.sharma.vesto.ui.intro.IntroScreen
import com.saksham.sharma.vesto.ui.screen.main.MainScreen

@Composable
fun AppNavGraph(
    navController: NavHostController = rememberNavController(),
    navigationProvider: NavigationProvider,
    isLoggedIn: Boolean
) {

    NavHost(
        navController = navController,
        startDestination = if (isLoggedIn) MainFeature.MAIN_SCREEN_ROUTE else "intro_screen"
    ) {
        composable("intro_screen") {
            IntroScreen(onPrimaryBtnClicked = {
                navController.navigate(AuthFeature.AUTH_SCREEN_ROUTE) {
                    popUpTo("intro_screen") { inclusive = true }
                }
            })
        }

        composable(MainFeature.MAIN_SCREEN_ROUTE) {
            MainScreen(
                navigationProvider = navigationProvider,
                topLevelNavController = navController
            )
        }

        navigationProvider.stockApi.registerGraph(navController, this)
        navigationProvider.newsApi.registerGraph(navController, this)
        navigationProvider.profileApi.registerGraph(navController, this)
        navigationProvider.authApi.registerGraph(navController, this)

    }
}