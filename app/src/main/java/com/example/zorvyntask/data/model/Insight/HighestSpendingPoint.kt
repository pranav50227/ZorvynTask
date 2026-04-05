package com.example.zorvyntask.data.model.Insight

data class HighestSpendingData(
    val category: String,
    val amount: String,
    val changePercent: Int,       // positive = increase
    val vsLabel: String
)
