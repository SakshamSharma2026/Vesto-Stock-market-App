package com.saksham.sharma.vesto.features.presentation.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.saksham.sharma.vesto.core.common.UiEvent
import com.saksham.sharma.vesto.core.network.ConnectivityObserver
import com.saksham.sharma.vesto.features.domain.usecases.StockUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
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

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState = _uiState.asStateFlow()

    init {
        observeNetwork()
        getIndianTrendingStock()
    }

    private fun observeNetwork() {
        connectivityObserver.observe()
            .onEach { status ->
                _uiState.update {
                    it.copy(
                        isOffline = status == ConnectivityObserver.Status.Unavailable ||
                                status == ConnectivityObserver.Status.Lost
                    )
                }
            }
            .launchIn(viewModelScope)
    }

    private fun getIndianTrendingStock() {
        stockUseCases.getIndianTrendingStock()
            .onEach { result ->

                when (result) {

                    is UiEvent.Loading -> {
                        _uiState.update {
                            it.copy(
                                stocksSection = it.stocksSection.copy(
                                    isLoading = true,
                                    error = null
                                )
                            )
                        }
                    }

                    is UiEvent.Success -> {
                        val gainers = result.data?.trending_stocks?.top_gainers
                            .orEmpty()

                        val losers = result.data?.trending_stocks?.top_losers
                            .orEmpty()

                        _uiState.update {
                            it.copy(
                                stocksSection = it.stocksSection.copy(
                                    isLoading = false,
                                    gainers = gainers,
                                    losers = losers,
                                    error = null
                                )
                            )
                        }
                    }

                    is UiEvent.Error -> {
                        _uiState.update {
                            it.copy(
                                stocksSection = it.stocksSection.copy(
                                    isLoading = false,
                                    error = result.message
                                )
                            )
                        }
                    }
                }
            }
            .launchIn(viewModelScope)
    }

    fun onTabSelected(tab: StockTab) {
        _uiState.update {
            it.copy(
                stocksSection = it.stocksSection.copy(
                    selectedTab = tab
                )
            )
        }
    }
}