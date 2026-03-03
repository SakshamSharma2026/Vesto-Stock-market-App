package com.saksham.sharma.vesto.features.presentation.screens.details

import com.saksham.sharma.vesto.features.data.model.StockDetailsResponse

data class StockDetailsUiState(
    val isLoading: Boolean = false,
    val data: StockDetailsResponse? = null,
    val error: String? = null
)