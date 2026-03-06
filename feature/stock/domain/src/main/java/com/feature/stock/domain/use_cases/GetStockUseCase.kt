package com.feature.stock.domain.use_cases

import com.core.common.UiEvent
import com.feature.stock.domain.repo.StockRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetStockUseCase @Inject constructor(private val stockRepository: StockRepository) {

    fun getIndianTrendingStock() =
        flow {
            emit(UiEvent.Loading())
            emit(UiEvent.Success(stockRepository.getIndianTrendingStock()))
        }.catch {
            emit(UiEvent.Error(it.message.toString()))
        }.flowOn(Dispatchers.IO)

    fun getStockDetails(stockName: String) = flow {
        emit(UiEvent.Loading())
        emit(UiEvent.Success(stockRepository.getStockDetails(stockName)))
    }.catch {
        emit(UiEvent.Error(it.message.toString()))
    }.flowOn(Dispatchers.IO)

}
