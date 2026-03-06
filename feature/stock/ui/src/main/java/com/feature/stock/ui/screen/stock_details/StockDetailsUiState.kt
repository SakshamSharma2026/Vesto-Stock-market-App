package com.feature.stock.ui.screen.stock_details

import androidx.compose.runtime.Immutable
import com.feature.stock.domain.model.StockDetailsData

enum class ChartRange {
    DAYS, WEEKS, MONTHS, YEARS, ALL
}

@Immutable
data class StockDetailsUiState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val selectedRange: ChartRange = ChartRange.MONTHS,
    val stockDetails: StockDetailsData? = null
)