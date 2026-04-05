package com.example.zorvyntask.data.model.Insight

import com.example.zorvyntask.ui.Screens.MonthlyTrendPoint

data class MonthlyTrendData(
    val points: List<MonthlyTrendPoint>,
    val highlightMonth: String,
    val highlightLabel: String,
    val selectedPeriod: String,   // "6 Months" | "1 Year"
    val periods: List<String>
)