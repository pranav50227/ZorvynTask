package com.example.zorvyntask.ui.Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.zorvyntask.data.model.transaction.FilterChip
import com.example.zorvyntask.data.model.transaction.TransactionCategory
import com.example.zorvyntask.data.model.transaction.TransactionItem
import com.example.zorvyntask.data.model.transaction.TransactionScreenData
import com.example.zorvyntask.ui.components.AppCard
import com.example.zorvyntask.ui.components.AppColumn
import com.example.zorvyntask.ui.components.AppRow
import com.example.zorvyntask.ui.components.AppSpacer
import com.example.zorvyntask.ui.components.AppText
import com.example.zorvyntask.ui.components.TextType
import com.example.zorvyntask.ui.theme.SpaceSize

// ─────────────────────────────────────────────────────────────────────────────
// Enums & Domain Models
// ─────────────────────────────────────────────────────────────────────────────

/** All possible transaction categories shown in the app. */


/** Whether money came in or went out. */
enum class TransactionType {
    INCOME, EXPENSE
}


// ─────────────────────────────────────────────────────────────────────────────
// Sample Data
// ─────────────────────────────────────────────────────────────────────────────

val sampleTransactions = listOf(
    TransactionItem(
        id = 1,
        title = "Monthly Salary",
        date = "Sept 01",
        category = TransactionCategory.SALARY,
        note = "Quarterly bonus included",
        amount = "\$8,400.00",
        type = TransactionType.INCOME
    ),
    TransactionItem(
        id       = 2,
        title    = "Whole Foods Market",
        date     = "Sept 03",
        category = TransactionCategory.GROCERIES,
        note     = "Weekly supplies",
        amount   = "\$142.50",
        type     = TransactionType.EXPENSE
    ),
    TransactionItem(
        id       = 3,
        title    = "Luxury Lofts Ltd.",
        date     = "Sept 05",
        category = TransactionCategory.RENT,
        note     = "Automatic payment",
        amount   = "\$2,200.00",
        type     = TransactionType.EXPENSE
    ),
    TransactionItem(
        id       = 4,
        title    = "Le Petit Atelier",
        date     = "Sept 07",
        category = TransactionCategory.DINING,
        note     = "Business lunch",
        amount   = "\$84.20",
        type     = TransactionType.EXPENSE
    ),
    TransactionItem(
        id       = 5,
        title    = "Stock Dividends",
        date     = "Sept 10",
        category = TransactionCategory.INVESTMENT,
        note     = "Portfolio yield",
        amount   = "\$312.15",
        type     = TransactionType.INCOME
    ),
    TransactionItem(
        id       = 6,
        title    = "Electric & Water Bill",
        date     = "Sept 12",
        category = TransactionCategory.UTILITIES,
        note     = "Monthly utilities",
        amount   = "\$180.00",
        type     = TransactionType.EXPENSE
    ),
    TransactionItem(
        id       = 7,
        title    = "Netflix & Spotify",
        date     = "Sept 14",
        category = TransactionCategory.ENTERTAINMENT,
        note     = "Subscription renewal",
        amount   = "\$28.98",
        type     = TransactionType.EXPENSE
    )
)

val sampleTransactionScreenData = TransactionScreenData(
    topBarTitle = "Zorvyn Task",
    searchHint = "Search transactions...",
    filters = listOf(
        FilterChip("Date", Icons.Default.CalendarMonth),
        FilterChip("Category", Icons.Default.Category),
        FilterChip("Type", Icons.Default.FilterList)
    ),
    defaultSelectedFilter = "Date",
    sectionTitle = "Recent Activity",
    sectionMonth = "APR 2026",
    transactions = sampleTransactions
)

// ─────────────────────────────────────────────────────────────────────────────
// Transaction Screen
// ─────────────────────────────────────────────────────────────────────────────

@Composable
fun TransactionScreen(
    data: TransactionScreenData = sampleTransactionScreenData
) {
    var selectedFilter by remember { mutableStateOf(data.defaultSelectedFilter) }
    var searchQuery    by remember { mutableStateOf("") }

    Scaffold(
        containerColor = BackgroundGray,
        floatingActionButton = { TransactionFab() }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp)
        ) {
            item { AppSpacer(SpaceSize.Medium) }

            // ── Top Bar ──────────────────────────────────────────────────────
            item {
                TransactionTopBar(title = data.topBarTitle)
            }
            item { AppSpacer(SpaceSize.Medium) }

            // ── Search Bar ───────────────────────────────────────────────────
            item {
                TransactionSearchBar(
                    query     = searchQuery,
                    hint      = data.searchHint,
                    onChanged = { searchQuery = it }
                )
            }
            item { AppSpacer(SpaceSize.Medium) }

            // ── Filter Chips ─────────────────────────────────────────────────
            item {
                TransactionFilterRow(
                    filters          = data.filters,
                    selectedLabel    = selectedFilter,
                    onFilterSelected = { selectedFilter = it }
                )
            }
            item { AppSpacer(SpaceSize.Large) }

            // ── Section Header ───────────────────────────────────────────────
            item {
                TransactionSectionHeader(
                    title = data.sectionTitle,
                    month = data.sectionMonth
                )
            }
            item { AppSpacer(SpaceSize.Medium) }

            // ── Transaction List ─────────────────────────────────────────────
            val filtered = if (searchQuery.isBlank()) data.transactions
            else data.transactions.filter {
                it.title.contains(searchQuery, ignoreCase = true) ||
                        it.category.label.contains(searchQuery, ignoreCase = true)
            }

            items(filtered, key = { it.id }) { tx ->
                TransactionCard(item = tx)
                AppSpacer(SpaceSize.Medium)
            }

            item { AppSpacer(SpaceSize.Large) }
        }
    }
}

// ─────────────────────────────────────────────────────────────────────────────
// Top Bar
// ─────────────────────────────────────────────────────────────────────────────

@Composable
fun TransactionTopBar(title: String) {
    AppRow(
        modifier          = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Avatar
        Box(
            modifier         = Modifier
                .size(46.dp)
                .clip(CircleShape)
                .background(Color(0xFF4A5568)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector     = Icons.Default.Person,
                contentDescription = "Avatar",
                tint            = Color.White,
                modifier        = Modifier.size(28.dp)
            )
        }
        AppSpacer(SpaceSize.Small, isHorizontal = true)
        AppText(
            text     = title,
            type     = TextType.Heading,
            color    = TextDark
        )
        Spacer(modifier = Modifier.weight(1f))
        Icon(
            imageVector        = Icons.Default.Search,
            contentDescription = "Search",
            tint               = TextDark,
            modifier           = Modifier.size(24.dp)
        )
        AppSpacer(SpaceSize.Small, isHorizontal = true)
        Icon(
            imageVector        = Icons.Default.Settings,
            contentDescription = "Settings",
            tint               = TextDark,
            modifier           = Modifier.size(24.dp)
        )
    }
}

// ─────────────────────────────────────────────────────────────────────────────
// Search Bar
// ─────────────────────────────────────────────────────────────────────────────

@Composable
fun TransactionSearchBar(
    query: String,
    hint: String,
    onChanged: (String) -> Unit
) {
    Surface(
        shape         = RoundedCornerShape(14.dp),
        color         = CardWhite,
        tonalElevation = 2.dp,
        modifier      = Modifier.fillMaxWidth()
    ) {
        AppRow(
            modifier          = Modifier
                .fillMaxWidth()
                .padding(horizontal = 14.dp, vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector        = Icons.Default.Search,
                contentDescription = "Search Icon",
                tint               = TextGray,
                modifier           = Modifier.size(20.dp)
            )
            AppSpacer(SpaceSize.Small, isHorizontal = true)
            TextField(
                value         = query,
                onValueChange = onChanged,
                placeholder   = {
                    AppText(text = hint, type = TextType.Body, color = TextGray)
                },
                singleLine    = true,
                colors        = TextFieldDefaults.colors(
                    focusedContainerColor   = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    focusedIndicatorColor   = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                modifier      = Modifier.fillMaxWidth()
            )
        }
    }
}

// ─────────────────────────────────────────────────────────────────────────────
// Filter Chips Row
// ─────────────────────────────────────────────────────────────────────────────

@Composable
fun TransactionFilterRow(
    filters: List<FilterChip>,
    selectedLabel: String,
    onFilterSelected: (String) -> Unit
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        contentPadding        = PaddingValues(horizontal = 0.dp)
    ) {
        items(filters, key = { it.label }) { chip ->
            val isSelected = chip.label == selectedLabel
            Surface(
                shape    = RoundedCornerShape(24.dp),
                color    = if (isSelected) GreenPrimary else CardWhite,
                modifier = Modifier
                    .clip(RoundedCornerShape(24.dp))
            ) {
                AppRow(
                    modifier          = Modifier.padding(
                        horizontal = 16.dp, vertical = 10.dp
                    ),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector        = chip.icon,
                        contentDescription = chip.label,
                        tint               = if (isSelected) Color.White else TextGray,
                        modifier           = Modifier.size(16.dp)
                    )
                    AppSpacer(SpaceSize.Small, isHorizontal = true)
                    AppText(
                        text  = chip.label,
                        type  = TextType.Body,
                        color = if (isSelected) Color.White else TextGray
                    )
                }
            }
        }
    }
}

// ─────────────────────────────────────────────────────────────────────────────
// Section Header
// ─────────────────────────────────────────────────────────────────────────────

@Composable
fun TransactionSectionHeader(title: String, month: String) {
    AppRow(
        modifier          = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AppText(
            text     = title,
            type     = TextType.Heading,
            color    = TextDark,
            modifier = Modifier.weight(1f)
        )
        AppText(
            text  = month,
            type  = TextType.Body,
            color = TextGray
        )
    }
}

// ─────────────────────────────────────────────────────────────────────────────
// Transaction Card
// ─────────────────────────────────────────────────────────────────────────────

@Composable
fun TransactionCard(item: TransactionItem) {
    val isIncome        = item.type == TransactionType.INCOME
    val amountColor     = if (isIncome) GreenPrimary else RedAccent
    val amountPrefix    = if (isIncome) "+ " else "- "
    val typeLabel       = if (isIncome) "INCOME" else "EXPENSE"
    val typeArrow       = if (isIncome) Icons.Default.ArrowUpward else Icons.Default.ArrowDownward

    AppCard(modifier = Modifier.fillMaxWidth()) {
        AppRow(
            modifier          = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Category icon box
            Box(
                modifier         = Modifier
                    .size(54.dp)
                    .clip(RoundedCornerShape(14.dp))
                    .background(item.category.iconBg),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector        = item.category.icon,
                    contentDescription = item.category.label,
                    tint               = item.category.iconTint,
                    modifier           = Modifier.size(26.dp)
                )
            }

            AppSpacer(SpaceSize.Medium, isHorizontal = true)

            // Title + meta
            AppColumn(modifier = Modifier.weight(1f)) {
                AppText(
                    text  = item.title,
                    type  = TextType.SubHeading,
                    color = TextDark
                )
                AppSpacer(SpaceSize.Small)
                AppText(
                    text  = "${item.date} • ${item.category.label}",
                    type  = TextType.Body,
                    color = TextGray
                )
                AppSpacer(SpaceSize.Small)
                // Italic note
                Text(
                    text       = item.note,
                    fontSize   = 13.sp,
                    fontStyle  = FontStyle.Italic,
                    color      = TextGray,
                    fontWeight = FontWeight.Normal
                )
            }

            AppSpacer(SpaceSize.Small, isHorizontal = true)

            // Amount + type badge
            AppColumn(horizontalAlignment = Alignment.End) {
                // "+" or nothing — small icon
                Icon(
                    imageVector        = if (isIncome) Icons.Default.Add else Icons.Default.Remove,
                    contentDescription = null,
                    tint               = amountColor,
                    modifier           = Modifier.size(16.dp)
                )
                AppText(
                    text  = "$amountPrefix${item.amount}",
                    type  = TextType.SubHeading,
                    color = amountColor
                )
                AppSpacer(SpaceSize.Small)
                AppRow(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector        = typeArrow,
                        contentDescription = typeLabel,
                        tint               = amountColor,
                        modifier           = Modifier.size(13.dp)
                    )
                    AppSpacer(SpaceSize.Small, isHorizontal = true)
                    AppText(
                        text  = typeLabel,
                        type  = TextType.Body,
                        color = amountColor
                    )
                }
            }
        }
    }
}

// ─────────────────────────────────────────────────────────────────────────────
// Floating Action Button
// ─────────────────────────────────────────────────────────────────────────────

@Composable
fun TransactionFab() {
    FloatingActionButton(
        onClick          = {},
        shape            = CircleShape,
        containerColor   = GreenPrimary,
        contentColor     = Color.White,
        modifier         = Modifier.size(56.dp)
    ) {
        Icon(
            imageVector        = Icons.Default.Add,
            contentDescription = "Add Transaction",
            modifier           = Modifier.size(26.dp)
        )
    }
}

// ─────────────────────────────────────────────────────────────────────────────
// Preview
// ─────────────────────────────────────────────────────────────────────────────

@Preview(showBackground = true, widthDp = 390, heightDp = 844)
@Composable
fun TransactionScreenPreview() {
    MaterialTheme {
        TransactionScreen()
    }
}