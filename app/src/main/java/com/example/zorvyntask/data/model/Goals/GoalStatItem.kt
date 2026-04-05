package com.example.zorvyntask.data.model.Goals

import com.example.zorvyntask.ui.Screens.GoalStatIcon

data class GoalStatItem(
    val iconType: GoalStatIcon,
    val value: String,               // e.g. "15 Days"
    val description: String          // e.g. "NO OVERSPENDING"
)