package com.feature.stock.ui.screen.stock_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.core.common.UiEvent
import com.feature.stock.domain.use_cases.GetStockUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class StockDetailsViewModel @Inject constructor(
    private val useCases: GetStockUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(StockDetailsUiState())
    val stockDetailsUiState: StateFlow<StockDetailsUiState> = _uiState.asStateFlow()

    fun loadStock(stockName: String) {
        useCases.getStockDetails(stockName)
            .onEach { result ->
                when (result) {
                    is UiEvent.Loading -> {
                        _uiState.update { it.copy(isLoading = true, error = null) }
                    }
                    is UiEvent.Error -> {
                        _uiState.update {
                            it.copy(
                                isLoading = false,
                                error = result.message ?: "Something went wrong"
                            )
                        }
                    }
                    is UiEvent.Success -> {
                        result.data?.let { response ->
                            _uiState.update {
                                it.copy(
                                    isLoading = false,
                                    stockDetails = response,
                                    error = null
                                )
                            }
                        }
                    }
                }
            }
            .launchIn(viewModelScope)
    }

    fun onRangeSelected(range: ChartRange) {
        _uiState.update { state ->
            state.copy(selectedRange = range)
        }
    }
}