package com.saksham.sharma.vesto.features.presentation.screens.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.saksham.sharma.vesto.BuildConfig
import com.saksham.sharma.vesto.core.common.UiEvent
import com.saksham.sharma.vesto.core.network.ConnectivityObserver
import com.saksham.sharma.vesto.features.domain.usecases.StockUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val stockUseCases: StockUseCases,
    private val connectivityObserver: ConnectivityObserver
) : ViewModel() {

    private val _indianStockList = MutableStateFlow(HomeUiState())
    val indianStockList: StateFlow<HomeUiState> = _indianStockList.asStateFlow()

    init {
        observeNetwork()
       getIndianTrendingStock(BuildConfig.API_KEY)
    }

    private fun observeNetwork() {
        connectivityObserver.observe().onEach { status ->
            _indianStockList.update {
                it.copy(isOffline = status == ConnectivityObserver.Status.Unavailable || status == ConnectivityObserver.Status.Lost)
            }
        }.launchIn(viewModelScope)
    }

    private fun getIndianTrendingStock(apiKey: String) {
        stockUseCases.getIndianTrendingStock(apiKey).onEach { result ->
            when (result) {
                is UiEvent.Loading -> {
                    _indianStockList.update { it.copy(isLoading = true) }
                }

                is UiEvent.Success -> {
                    Log.d("HomeViewModel", "API SUCCESS: ${result.data}")
                    _indianStockList.update { it.copy(isLoading = false, data = result.data) }
                }

                is UiEvent.Error -> {
                    Log.e("HomeViewModel", "API ERROR: ${result.message}")
                    _indianStockList.update {
                        it.copy(
                            isLoading = false,
                            errorMsg = result.message ?: "An unexpected error occurred"
                        )
                    }
                }
            }
        }.launchIn(viewModelScope)
    }
}