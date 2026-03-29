package com.saksham.sharma.vesto.ui.screen.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.core.common.navigation_constant.NewsFeature
import com.core.common.navigation_constant.ProfileFeature
import com.core.common.navigation_constant.StockFeature
import com.feature.stock.ui.components.VestoBottomBar
import com.feature.stock.ui.components.SystemBarStyle
import com.core.common.bgColor
import com.saksham.sharma.vesto.navigation.NavigationProvider

@Composable
fun MainScreen(
    navigationProvider: NavigationProvider,
    bottomNavController: NavHostController = rememberNavController(),
    topLevelNavController: NavHostController // To navigate to details etc.
) {
    val navBackStackEntry by bottomNavController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    SystemBarStyle(darkIcons = true, statusBarColor = bgColor)


    Scaffold(
        containerColor = bgColor,
        bottomBar = {
            // Reusing the VestoBottomBar from the stock module
            VestoBottomBar(
                selectedRoute = currentRoute,
                onHomeClick = {
                    bottomNavController.navigate(StockFeature.STOCK_SCREEN_ROUTE) {
                        popUpTo(StockFeature.STOCK_SCREEN_ROUTE) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                onNewsClick = {
                    bottomNavController.navigate(NewsFeature.NEWS_SCREEN_ROUTE) {
                        popUpTo(StockFeature.STOCK_SCREEN_ROUTE) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                onProfileClick = {
                    bottomNavController.navigate(ProfileFeature.PROFILE_SCREEN_ROUTE) {
                        popUpTo(StockFeature.STOCK_SCREEN_ROUTE) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            NavHost(
                navController = bottomNavController,
                startDestination = StockFeature.STOCK_SCREEN_ROUTE,
                modifier = Modifier.fillMaxSize()
            ) {
                // We only register the main screens here for the tabs
                // But wait, registerGraph registers everything.
                // We might need to split it or handle navigation carefully.
                navigationProvider.stockApi.registerGraph(topLevelNavController, this)
                navigationProvider.newsApi.registerGraph(topLevelNavController, this)
                navigationProvider.profileApi.registerGraph(topLevelNavController, this)
            }
        }
    }
}
