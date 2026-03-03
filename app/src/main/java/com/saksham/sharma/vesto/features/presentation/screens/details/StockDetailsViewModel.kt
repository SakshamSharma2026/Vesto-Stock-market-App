package com.saksham.sharma.vesto.features.presentation.screens.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.saksham.sharma.vesto.core.common.UiEvent
import com.saksham.sharma.vesto.features.domain.usecases.StockUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import javax.inject.Inject


@HiltViewModel
class StockDetailsViewModel @Inject constructor(private val useCases: StockUseCases) : ViewModel() {

    private val _uiState = MutableStateFlow(StockDetailsUiState())
    val stockDetailsUiState: StateFlow<StockDetailsUiState> = _uiState


    fun getStockDetails(stockName: String) {
        useCases.getStockDetails(stockName).onEach { result ->
            when (result) {
                is UiEvent.Loading -> {
                    _uiState.update { it.copy(isLoading = true) }
                }

                is UiEvent.Error -> {
                    _uiState.update {
                        it.copy(
                            isLoading = false, error = error(message = it.error.toString())
                        )
                    }
                }

                is UiEvent.Success -> {
                    _uiState.update {
                        it.copy(isLoading = false, data = result.data)
                    }
                }

            }
        }.launchIn(viewModelScope)

    }
}