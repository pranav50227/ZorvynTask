package com.example.zorvyntask.data.model.Goals

data class GoalDetailData(
    val goalName: String,            // e.g. "Dream Vacation"
    val progressPercent: Int,        // e.g. 70
    val remainingAmount: String,     // e.g. "$1,500"
    val motivationSuffix: String,    // e.g. "to go until your dream getaway."
    val targetLabel: String,         // e.g. "Target:"
    val targetAmount: String         // e.g. "$5,000"
)