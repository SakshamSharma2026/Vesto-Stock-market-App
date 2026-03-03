package com.saksham.sharma.vesto.core.common

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.saksham.sharma.vesto.features.presentation.screens.details.StockDetailsScreen
import com.saksham.sharma.vesto.features.presentation.screens.home.HomeScreen
import com.saksham.sharma.vesto.features.presentation.screens.intro.IntroScreen

@Composable
fun AppNavGraph(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    Surface(modifier.fillMaxSize()) {
        NavHost(navController, startDestination = Screen.HomeScreen.route) {
            composable(route = Screen.IntroScreen.route) {
                IntroScreen(modifier, onPrimaryBtnClicked = {
                    navController.navigate(Screen.HomeScreen.route)
                })
            }
            composable(route = Screen.HomeScreen.route) {
                HomeScreen(
                    onStockClicked = { stockName ->
                        navController.navigate(Screen.StockDetailsScreen.createRoute(stockName))
                    }
                )
            }
            composable(
                route = Screen.StockDetailsScreen.route,
                arguments = listOf(navArgument("stockName") { type = NavType.StringType })
            ) { backStackEntry ->
                val stockName = backStackEntry.arguments?.getString("stockName") ?: "Unknown"
                StockDetailsScreen(
                    stockName = stockName,
                    onBackClicked = { navController.popBackStack() }
                )
            }
        }
    }
}