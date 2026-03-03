package com.saksham.sharma.vesto.features.presentation.screens.home

data class HomeUiState(
    val isOffline: Boolean = false,
    val stocksSection: StocksSectionUiState = StocksSectionUiState()
)