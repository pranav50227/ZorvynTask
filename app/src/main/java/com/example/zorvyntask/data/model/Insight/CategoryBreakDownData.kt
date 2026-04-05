package com.example.zorvyntask.data.model.Insight

import com.example.zorvyntask.ui.Screens.CategoryBreakdownItem

data class CategoryBreakdownData(
    val total: String,
    val items: List<CategoryBreakdownItem>
)
