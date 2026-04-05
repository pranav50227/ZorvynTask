package com.example.zorvyntask.data.model.transaction

data class TransactionScreenData(
    val topBarTitle: String,
    val searchHint: String,
    val filters: List<FilterChip>,
    val defaultSelectedFilter: String,
    val sectionTitle: String,
    val sectionMonth: String,
    val transactions: List<TransactionItem>
)