package com.saksham.sharma.vesto.features.presentation.screens.home

import com.saksham.sharma.vesto.features.data.model.IndianStockModelResponse

data class HomeUiState(
    val isLoading: Boolean = false,
    val errorMsg: String? = null,
    val data: IndianStockModelResponse? = null,
    val isOffline: Boolean = false
)