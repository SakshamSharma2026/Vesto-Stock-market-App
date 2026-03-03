package com.saksham.sharma.vesto.features.domain.model

import androidx.compose.ui.graphics.Color

data class StoryItemInfo(
    val label: String,
    val iconRes: Int,
    val ringColors: List<Color>,
    val bg: Color
)