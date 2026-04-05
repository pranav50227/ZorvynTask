package com.example.zorvyntask.data.model.Insight

data class WeeklyComparisonData(
    val thisWeekLabel: String,
    val thisWeekAmount: String,
    val thisWeekProgress: Float,  // 0f–1f
    val lastWeekLabel: String,
    val lastWeekAmount: String,
    val lastWeekProgress: Float,
    val savingPercent: Int,
    val projectedSaving: String
)