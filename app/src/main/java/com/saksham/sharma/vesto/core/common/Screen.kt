package com.saksham.sharma.vesto.core.common

sealed class Screen(val route: String) {
    data object IntroScreen : Screen(route = "intro_screen")
    data object HomeScreen : Screen(route = "home_screen")

    data object StockDetailsScreen : Screen(route = "stock_details_screen/{stockName}") {
        fun createRoute(stockName: String): String = "stock_details_screen/$stockName"
    }
}
