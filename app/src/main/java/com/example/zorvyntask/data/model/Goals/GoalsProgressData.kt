package com.example.zorvyntask.data.model.Goals

data class GoalProgressData(
    val progressLabel: String,       // e.g. "CURRENT PROGRESS"
    val progressPercent: Int,        // e.g. 70
    val savedAmount: String          // e.g. "$3,500"
)