package com.feature.stock.ui.screen.add_money

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.feature.stock.domain.use_cases.GetWalletUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WalletViewModel @Inject constructor(
    private val getWalletUseCase: GetWalletUseCase
) : ViewModel() {

    private val _balance = MutableStateFlow(0.0)
    val balance = _balance.asStateFlow()

    init {
        getWalletUseCase.getWalletDetails().onEach { wallet ->
            _balance.update { wallet?.balance ?: 0.0 }
        }.launchIn(viewModelScope)
    }

    fun addMoney(amount: Double) {
        viewModelScope.launch {
            val currentBalance = _balance.value
            getWalletUseCase.addMoney(currentBalance + amount)
        }
    }
}