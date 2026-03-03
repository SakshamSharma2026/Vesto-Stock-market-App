package com.saksham.sharma.vesto.features.presentation.screens.home

import com.saksham.sharma.vesto.features.data.model.TopGainer
import com.saksham.sharma.vesto.features.data.model.TopLoser
import com.saksham.sharma.vesto.features.data.model.TrendingStocks

enum class StockTab(val title: String) {
    GAINERS("Top Gainers"),
    LOSERS("Top Losers"),
    FAVOURITES("Favourites")
}

data class StocksSectionUiState(
    val isLoading: Boolean = false,
    val selectedTab: StockTab = StockTab.GAINERS,
    val gainers: List<TopGainer> = emptyList(),
    val losers: List<TopLoser> = emptyList(),
    val error: String? = null
)