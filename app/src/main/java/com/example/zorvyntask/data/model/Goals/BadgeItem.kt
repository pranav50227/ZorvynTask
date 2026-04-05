package com.example.zorvyntask.data.model.Goals

import com.example.zorvyntask.ui.Screens.BadgeIconType

data class BadgeItem(
    val iconType: BadgeIconType,
    val title: String,               // e.g. "Budget Master"
    val subtitle: String             // e.g. "30 day streak"
)