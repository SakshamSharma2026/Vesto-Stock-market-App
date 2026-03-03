package com.saksham.sharma.vesto.features.domain.model

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color

@Immutable
data class StoryItemInfoModel(
    val label: String,
    val iconRes: Int,
    val ringColors: List<Color>,
    val bg: Color
)