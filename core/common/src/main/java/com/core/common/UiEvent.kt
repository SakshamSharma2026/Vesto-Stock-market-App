package com.core.common

sealed class UiEvent<T> {
    class Loading : UiEvent<Nothing>()
    class Success<T>(val data: T) : UiEvent<T>()
    class Error(val message: String) : UiEvent<Nothing>()
}