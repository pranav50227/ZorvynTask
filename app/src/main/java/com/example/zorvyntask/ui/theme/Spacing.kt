package com.example.zorvyntask.ui.theme

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

enum class SpaceSize {
    None,
    Small,
    Medium,
    Large
}

fun spaceValue(size: SpaceSize): Dp {
    return when (size) {
        SpaceSize.None -> 0.dp
        SpaceSize.Small -> 8.dp
        SpaceSize.Medium -> 16.dp
        SpaceSize.Large -> 24.dp
    }
}