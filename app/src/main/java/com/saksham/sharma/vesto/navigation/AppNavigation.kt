package com.saksham.sharma.vesto.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.core.common.navigation_constant.StockFeature
import com.saksham.sharma.vesto.ui.intro.IntroScreen

@Composable
fun AppNavGraph(
    navController: NavHostController = rememberNavController(),
    navigationProvider: NavigationProvider
) {

    NavHost(navController = navController, startDestination = "intro_screen") {
        composable("intro_screen") {
            IntroScreen(onPrimaryBtnClicked = {
                navController.navigate(StockFeature.STOCK_SCREEN_ROUTE) {
                    popUpTo("intro_screen") { inclusive = true }
                }
            })
        }
        navigationProvider.stockApi.registerGraph(navController, this)
    }
}