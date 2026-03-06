package com.feature.stock.ui.model

import androidx.compose.runtime.Immutable


@Immutable
data class Company(
    val name: String,
    val ticker: String,
    val iconPath: Int
)