package com.feature.news.ui.screen.news

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.core.common.UiEvent
import com.core.network.connectivity.ConnectivityObserver
import com.feature.news.domain.use_cases.NewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val newsUseCase: NewsUseCase,
    private val connectivityObserver: ConnectivityObserver
) : ViewModel() {

    private val _uiState = MutableStateFlow(NewsUiState())
    val uiState = _uiState.asStateFlow()

    init {
        observeNetwork()
        getNews()
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

    private fun getNews() {
        newsUseCase()
            .onEach { result ->
                when (result) {
                    is UiEvent.Loading -> {
                        _uiState.update { it.copy(isLoading = true, error = null) }
                    }
                    is UiEvent.Success -> {
                        _uiState.update { it.copy(isLoading = false, news = result.data ?: emptyList(), error = null) }
                    }
                    is UiEvent.Error -> {
                        _uiState.update { it.copy(isLoading = false, error = result.message) }
                    }
                }
            }
            .launchIn(viewModelScope)
    }
}
