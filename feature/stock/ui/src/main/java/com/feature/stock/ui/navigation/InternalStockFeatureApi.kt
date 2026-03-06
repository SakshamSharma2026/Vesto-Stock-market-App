package com.feature.stock.ui.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.core.common.navigation_constant.StockFeature
import com.core.feature_api.FeatureApi
import com.feature.stock.ui.screen.stock.StocksScreen
import com.feature.stock.ui.screen.stock.StocksViewModel

internal object InternalStockFeatureApi : FeatureApi {
    override fun registerGraph(
        navController: androidx.navigation.NavHostController,
        navGraphBuilder: androidx.navigation.NavGraphBuilder
    ) {
        navGraphBuilder.navigation(
            startDestination = StockFeature.STOCK_NESTED_ROUTE,
            route = StockFeature.STOCK_SCREEN_ROUTE
        ) {
            composable(StockFeature.STOCK_NESTED_ROUTE) {
                val viewModel = hiltViewModel<StocksViewModel>()
                StocksScreen(viewModel, onStockClicked = { stockName ->
                    val route =
                        StockFeature.STOCK_DETAILS_SCREEN_ROUTE.replace("{stock_name}", stockName)
                    navController.navigate(route)
                })
            }

            composable(StockFeature.STOCK_DETAILS_SCREEN_ROUTE) { backStackEntry ->
                val stockName = backStackEntry.arguments?.getString("stock_name") ?: ""
                com.feature.stock.ui.screen.stock_details.StockDetailsScreen(
                    stockName = stockName,
                    onBackClicked = { navController.popBackStack() }
                )
            }
        }
    }
}