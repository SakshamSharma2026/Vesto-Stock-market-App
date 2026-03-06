package com.feature.stock.ui.screen.stock

data class StocksUiState(
    val isOffline: Boolean = false,
    val stocksSection: StocksSectionUiState = StocksSectionUiState()
)