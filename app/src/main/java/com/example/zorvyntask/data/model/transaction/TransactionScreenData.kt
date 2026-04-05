package com.example.zorvyntask.data.model.transaction

import com.example.zorvyntask.ui.Screens.FilterChip
import com.example.zorvyntask.ui.Screens.TransactionItem

data class TransactionScreenData(
    val topBarTitle: String,
    val searchHint: String,
    val filters: List<FilterChip>,
    val defaultSelectedFilter: String,
    val sectionTitle: String,
    val sectionMonth: String,
    val transactions: List<TransactionItem>
)