package com.example.zorvyntask.data.model.transaction

import com.example.zorvyntask.ui.Screens.TransactionType

data class TransactionItem(
    val id: Int,
    val title: String,
    val date: String,                    // e.g. "Sept 01"
    val category: TransactionCategory,
    val note: String,                    // italic sub-line
    val amount: String,                  // e.g. "$8,400.00"
    val type: TransactionType
)

