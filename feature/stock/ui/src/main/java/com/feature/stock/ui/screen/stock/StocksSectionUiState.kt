package com.feature.stock.ui.screen.stock

import com.feature.stock.domain.model.StockItem

enum class StockTab(val title: String) {
    GAINERS("Top Gainers"),
    LOSERS("Top Losers"),
}

data class StocksSectionUiState(
    val isLoading: Boolean = false,
    val selectedTab: StockTab = StockTab.GAINERS,
    val gainers: List<StockItem> = emptyList(),
    val losers: List<StockItem> = emptyList(),
    val error: String? = null
)