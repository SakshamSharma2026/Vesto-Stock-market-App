package com.feature.stock.ui.screen.stock

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.core.common.UiEvent
import com.core.common.User
import com.core.network.connectivity.ConnectivityObserver
import com.feature.auth.domain.usecase.AuthUseCase
import com.feature.stock.domain.use_cases.GetStockUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class StocksViewModel @Inject constructor(
    private val stockUseCases: GetStockUseCase,
    private val authUseCase: AuthUseCase,
    private val connectivityObserver: ConnectivityObserver
) : ViewModel() {

    val userData: StateFlow<User?> = authUseCase.getUser()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = null
        )

    private val _uiState = MutableStateFlow(StocksUiState())
    val uiState = _uiState.asStateFlow()

    init {
        observeNetwork()
        getTrendingStock()
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

    private fun getTrendingStock() {
        stockUseCases.getTrendingStocks()
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
                        val gainers = result.data.gainers

                        val losers = result.data.losers

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