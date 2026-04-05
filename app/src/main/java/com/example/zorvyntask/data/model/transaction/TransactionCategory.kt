package com.example.zorvyntask.data.model.transaction

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bolt
import androidx.compose.material.icons.filled.DirectionsCar
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MovieFilter
import androidx.compose.material.icons.filled.Payments
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material.icons.filled.Savings
import androidx.compose.material.icons.filled.ShoppingBag
import androidx.compose.material.icons.filled.TrendingUp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

enum class TransactionCategory(
    val label: String,
    val icon: ImageVector,
    val iconTint: Color,
    val iconBg: Color
) {
    SALARY(
        label      = "Design Services",
        icon       = Icons.Default.Payments,
        iconTint   = Color(0xFF1A8A5A),
        iconBg     = Color(0xFFE8F5EF)
    ),
    GROCERIES(
        label      = "Groceries",
        icon       = Icons.Default.ShoppingBag,
        iconTint   = Color(0xFFD32F2F),
        iconBg     = Color(0xFFFCE8E8)
    ),
    RENT(
        label      = "Rent",
        icon       = Icons.Default.Home,
        iconTint   = Color(0xFF455A64),
        iconBg     = Color(0xFFECEFF1)
    ),
    DINING(
        label      = "Dining",
        icon       = Icons.Default.Restaurant,
        iconTint   = Color(0xFFD32F2F),
        iconBg     = Color(0xFFFCE8E8)
    ),
    INVESTMENT(
        label      = "Investment",
        icon       = Icons.Default.TrendingUp,
        iconTint   = Color(0xFF1A8A5A),
        iconBg     = Color(0xFFE8F5EF)
    ),
    UTILITIES(
        label      = "Utilities",
        icon       = Icons.Default.Bolt,
        iconTint   = Color(0xFFF57C00),
        iconBg     = Color(0xFFFFF3E0)
    ),
    TRANSPORT(
        label      = "Transport",
        icon       = Icons.Default.DirectionsCar,
        iconTint   = Color(0xFF1565C0),
        iconBg     = Color(0xFFE3F2FD)
    ),
    ENTERTAINMENT(
        label      = "Entertainment",
        icon       = Icons.Default.MovieFilter,
        iconTint   = Color(0xFF6A1B9A),
        iconBg     = Color(0xFFF3E5F5)
    ),
    HEALTH(
        label      = "Health",
        icon       = Icons.Default.FavoriteBorder,
        iconTint   = Color(0xFFC62828),
        iconBg     = Color(0xFFFFEBEE)
    ),
    SAVINGS(
        label      = "Savings",
        icon       = Icons.Default.Savings,
        iconTint   = Color(0xFF00695C),
        iconBg     = Color(0xFFE0F2F1)
    )
}