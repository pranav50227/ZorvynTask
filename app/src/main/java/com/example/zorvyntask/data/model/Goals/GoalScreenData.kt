package com.example.zorvyntask.data.model.Goals

data class GoalsScreenData(
    val topBarTitle: String,
    val progress: GoalProgressData,
    val goalDetail: GoalDetailData,
    val stats: List<GoalStatItem>,
    val recentBadgesLabel: String,
    val viewCollectionLabel: String,
    val badges: List<BadgeItem>,
    val addSavingsLabel: String
)