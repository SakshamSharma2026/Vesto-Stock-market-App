package com.feature.news.domain.use_cases

import com.core.common.UiEvent
import com.feature.news.domain.repo.NewsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class NewsUseCase @Inject constructor(private val newsRepository: NewsRepository) {
    operator fun invoke() = flow {
        emit(UiEvent.Loading())
        newsRepository.getNews().collect {
            emit(UiEvent.Success(it))
        }
    }.catch {
        emit(UiEvent.Error(it.message ?: "An unexpected error occurred"))
    }.flowOn(Dispatchers.IO)
}