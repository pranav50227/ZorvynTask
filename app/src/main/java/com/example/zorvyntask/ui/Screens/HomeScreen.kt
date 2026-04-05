package com.example.zorvyntask.ui.Screens

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBalance
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.CreditCard
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PieChart
import androidx.compose.material.icons.filled.QrCodeScanner
import androidx.compose.material.icons.filled.ReceiptLong
import androidx.compose.material.icons.filled.Savings
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.zorvyntask.ui.components.AppCard
import com.example.zorvyntask.ui.components.AppColumn
import com.example.zorvyntask.ui.components.AppRow
import com.example.zorvyntask.ui.components.AppSpacer
import com.example.zorvyntask.ui.components.AppText
import com.example.zorvyntask.ui.components.TextType
import com.example.zorvyntask.ui.theme.SpaceSize

// ─── Theme Colors ──────────────────────────────────────────────────────────────
val GreenPrimary = Color(0xFF1A8A5A)
val GreenLight   = Color(0xFF4CAF82)
val RedAccent    = Color(0xFFD32F2F)
val BackgroundGray = Color(0xFFF0F2F5)
val CardWhite    = Color.White
val TextDark     = Color(0xFF1A1A2E)
val TextGray     = Color(0xFF6B7280)

// ─── Home Screen ───────────────────────────────────────────────────────────────
@Composable
fun HomeScreen() {
    Scaffold(
        containerColor = BackgroundGray
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(0.dp)
        ) {
            // Top App Bar
            item { AppSpacer(size = SpaceSize.Medium) }
            item { TopBar() }
            item { AppSpacer(size = SpaceSize.Medium) }

            // Current Balance Card
            item { BalanceCard() }
            item { AppSpacer(size = SpaceSize.Medium) }

            // Savings Progress Card
            item { SavingsProgressCard() }
            item { AppSpacer(size = SpaceSize.Medium) }

            // Breakdown Card
            item { BreakdownCard() }
            item { AppSpacer(size = SpaceSize.Medium) }

            // Weekly Trend Card
            item { WeeklyTrendCard() }
            item { AppSpacer(size = SpaceSize.Medium) }

            // Quick Action Buttons
            item { QuickActionsRow() }
            item { AppSpacer(size = SpaceSize.Medium) }
        }
    }
}

// ─── Top Bar ───────────────────────────────────────────────────────────────────
@Composable
fun TopBar() {
    AppRow(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Avatar
        Box(
            modifier = Modifier
                .size(44.dp)
                .clip(CircleShape)
                .background(Color(0xFF4DB6AC)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = "Avatar",
                tint = Color.White,
                modifier = Modifier.size(28.dp)
            )
        }

        AppSpacer(size = SpaceSize.Small, isHorizontal = true)

        AppText(
            text = "Pranav Yadav",
            type = TextType.Heading,
            color = GreenPrimary
        )

        Spacer(modifier = Modifier.weight(1f))

        Icon(
            imageVector = Icons.Default.Settings,
            contentDescription = "Settings",
            tint = TextDark,
            modifier = Modifier.size(24.dp)
        )
    }
}

// ─── Balance Card ──────────────────────────────────────────────────────────────
@Composable
fun BalanceCard() {
    AppCard(modifier = Modifier.fillMaxWidth()) {
        AppColumn(modifier = Modifier.padding(16.dp)) {
            AppText(
                text = "CURRENT BALANCE",
                type = TextType.Body,
                color = TextGray
            )
            AppSpacer(size = SpaceSize.Small)
            AppText(
                text = "$12,450.00",
                type = TextType.Heading,
                color = TextDark,
                modifier = Modifier.wrapContentWidth()
            )
            // Override heading size for large balance display
            AppSpacer(size = SpaceSize.Medium)
            AppRow(modifier = Modifier.fillMaxWidth()) {
                // Income Box
                AppCard(
                    modifier = Modifier.weight(1f),
                    containerColor = BackgroundGray,
                    elevation = 0.dp
                ) {
                    AppColumn(modifier = Modifier.padding(12.dp)) {
                        AppRow(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.KeyboardArrowDown,
                                contentDescription = "Income",
                                tint = GreenPrimary,
                                modifier = Modifier.size(16.dp)
                            )
                            AppSpacer(size = SpaceSize.Small, isHorizontal = true)
                            AppText(
                                text = "INCOME",
                                type = TextType.Body,
                                color = TextGray
                            )
                        }
                        AppSpacer(size = SpaceSize.Small)
                        AppText(
                            text = "$4,500.00",
                            type = TextType.SubHeading,
                            color = GreenPrimary
                        )
                    }
                }

                AppSpacer(size = SpaceSize.Small, isHorizontal = true)

                // Expenses Box
                AppCard(
                    modifier = Modifier.weight(1f),
                    containerColor = BackgroundGray,
                    elevation = 0.dp
                ) {
                    AppColumn(modifier = Modifier.padding(12.dp)) {
                        AppRow(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.KeyboardArrowUp,
                                contentDescription = "Expenses",
                                tint = RedAccent,
                                modifier = Modifier.size(16.dp)
                            )
                            AppSpacer(size = SpaceSize.Small, isHorizontal = true)
                            AppText(
                                text = "EXPENSES",
                                type = TextType.Body,
                                color = TextGray
                            )
                        }
                        AppSpacer(size = SpaceSize.Small)
                        AppText(
                            text = "$2,100.00",
                            type = TextType.SubHeading,
                            color = RedAccent
                        )
                    }
                }
            }
        }
    }
}

// ─── Savings Progress Card ─────────────────────────────────────────────────────
@Composable
fun SavingsProgressCard() {
    AppCard(modifier = Modifier.fillMaxWidth()) {
        AppRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AppColumn(modifier = Modifier.weight(1f)) {
                AppText(
                    text = "Savings Progress",
                    type = TextType.SubHeading,
                    color = TextDark
                )
                AppSpacer(size = SpaceSize.Small)
                AppText(
                    text = "New Car Fund",
                    type = TextType.Body,
                    color = TextGray
                )
                AppSpacer(size = SpaceSize.Small)
                AppText(
                    text = "$15,000 / $20,000",
                    type = TextType.SubHeading,
                    color = GreenPrimary
                )
            }

            // Circular Progress Indicator
            CircularProgressIndicatorWithLabel(
                progress = 0.75f,
                label = "75%"
            )
        }
    }
}

@Composable
fun CircularProgressIndicatorWithLabel(progress: Float, label: String) {
    Box(
        modifier = Modifier.size(72.dp),
        contentAlignment = Alignment.Center
    ) {
        Canvas(modifier = Modifier.size(72.dp)) {
            val strokeWidth = 8.dp.toPx()
            val radius = (size.minDimension - strokeWidth) / 2
            val center = Offset(size.width / 2, size.height / 2)

            // Background arc
            drawArc(
                color = Color(0xFFE0E0E0),
                startAngle = -90f,
                sweepAngle = 360f,
                useCenter = false,
                topLeft = Offset(center.x - radius, center.y - radius),
                size = Size(radius * 2, radius * 2),
                style = Stroke(width = strokeWidth, cap = StrokeCap.Round)
            )

            // Progress arc
            drawArc(
                color = GreenPrimary,
                startAngle = -90f,
                sweepAngle = 360f * progress,
                useCenter = false,
                topLeft = Offset(center.x - radius, center.y - radius),
                size = Size(radius * 2, radius * 2),
                style = Stroke(width = strokeWidth, cap = StrokeCap.Round)
            )
        }
        AppText(
            text = label,
            type = TextType.Body,
            color = TextDark,
            textAlign = TextAlign.Center
        )
    }
}

// ─── Breakdown Card ────────────────────────────────────────────────────────────
@Composable
fun BreakdownCard() {
    AppCard(modifier = Modifier.fillMaxWidth()) {
        AppColumn(modifier = Modifier.padding(16.dp)) {
            AppRow(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                AppText(
                    text = "Breakdown",
                    type = TextType.SubHeading,
                    color = TextDark
                )
                Spacer(modifier = Modifier.weight(1f))
                Icon(
                    imageVector = Icons.Default.PieChart,
                    contentDescription = "Pie Chart",
                    tint = TextDark,
                    modifier = Modifier.size(24.dp)
                )
            }

            AppSpacer(size = SpaceSize.Medium)

            AppRow(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Pie chart canvas
                PieChart(
                    modifier = Modifier.size(80.dp),
                    slices = listOf(
                        PieSlice(Color(0xFF1A8A5A), 40f),  // Rent - green
                        PieSlice(Color(0xFF4CAF82), 30f),  // Food - light green
                        PieSlice(Color(0xFFD32F2F), 30f),  // Fun - red
                    )
                )

                AppSpacer(size = SpaceSize.Medium, isHorizontal = true)

                AppColumn(modifier = Modifier.weight(1f)) {
                    BreakdownItem(color = Color(0xFF1A8A5A), label = "Rent", percentage = "40%")
                    AppSpacer(size = SpaceSize.Small)
                    BreakdownItem(color = Color(0xFF4CAF82), label = "Food", percentage = "30%")
                    AppSpacer(size = SpaceSize.Small)
                    BreakdownItem(color = Color(0xFFD32F2F), label = "Fun", percentage = "30%")
                }
            }
        }
    }
}

data class PieSlice(val color: Color, val sweep: Float)

@Composable
fun PieChart(modifier: Modifier = Modifier, slices: List<PieSlice>) {
    Canvas(modifier = modifier) {
        var startAngle = -90f
        slices.forEach { slice ->
            drawArc(
                color = slice.color,
                startAngle = startAngle,
                sweepAngle = slice.sweep / 100f * 360f,
                useCenter = true,
                size = size
            )
            startAngle += slice.sweep / 100f * 360f
        }
    }
}

@Composable
fun BreakdownItem(color: Color, label: String, percentage: String) {
    AppRow(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(10.dp)
                .clip(CircleShape)
                .background(color)
        )
        AppSpacer(size = SpaceSize.Small, isHorizontal = true)
        AppText(
            text = label,
            type = TextType.Body,
            color = TextDark,
            modifier = Modifier.weight(1f)
        )
        AppText(
            text = percentage,
            type = TextType.Body,
            color = TextDark
        )
    }
}

// ─── Weekly Trend Card ─────────────────────────────────────────────────────────
@Composable
fun WeeklyTrendCard() {
    AppCard(modifier = Modifier.fillMaxWidth()) {
        AppColumn(modifier = Modifier.padding(16.dp)) {
            AppRow(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                AppColumn(modifier = Modifier.weight(1f)) {
                    AppText(
                        text = "Weekly Trend",
                        type = TextType.SubHeading,
                        color = TextDark
                    )
                    AppSpacer(size = SpaceSize.Small)
                    AppText(
                        text = "-12% decline from last week",
                        type = TextType.Body,
                        color = TextGray
                    )
                }
                Surface(
                    shape = RoundedCornerShape(20.dp),
                    color = BackgroundGray
                ) {
                    AppText(
                        text = "Details",
                        type = TextType.Body,
                        color = TextDark,
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                    )
                }
            }

            AppSpacer(size = SpaceSize.Large)

            // Bar Chart placeholder (Mon–Sun)
            WeeklyBarChart()

            AppSpacer(size = SpaceSize.Small)

            // Day labels
            AppRow(modifier = Modifier.fillMaxWidth()) {
                listOf("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun").forEach { day ->
                    AppText(
                        text = day,
                        type = TextType.Body,
                        color = TextGray,
                        modifier = Modifier.weight(1f),
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}

@Composable
fun WeeklyBarChart() {
    val barHeights = listOf(0.7f, 0.5f, 0.8f, 0.4f, 0.6f, 0.3f, 0.5f)
    AppRow(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp),
        verticalAlignment = Alignment.Bottom
    ) {
        barHeights.forEach { heightFraction ->
            Box(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 4.dp)
                    .fillMaxHeight(heightFraction)
                    .clip(RoundedCornerShape(topStart = 4.dp, topEnd = 4.dp))
                    .background(GreenPrimary.copy(alpha = 0.3f))
            )
        }
    }
}

// ─── Quick Actions Row ─────────────────────────────────────────────────────────
@Composable
fun QuickActionsRow() {
    AppCard(modifier = Modifier.fillMaxWidth()) {
        AppRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            QuickActionItem(icon = Icons.Default.Send, label = "SEND")
            QuickActionItem(icon = Icons.Default.Add, label = "ADD")
            QuickActionItem(icon = Icons.Default.QrCodeScanner, label = "SCAN")
            QuickActionItem(icon = Icons.Default.AccountBalance, label = "BANK")
        }
    }
}

@Composable
fun QuickActionItem(icon: androidx.compose.ui.graphics.vector.ImageVector, label: String) {
    AppColumn(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape)
                .background(BackgroundGray),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = label,
                tint = GreenPrimary,
                modifier = Modifier.size(22.dp)
            )
        }
        AppSpacer(size = SpaceSize.Small)
        AppText(
            text = label,
            type = TextType.Body,
            color = TextGray
        )
    }
}


// ─── Preview ───────────────────────────────────────────────────────────────────
@Preview(showBackground = true, widthDp = 390, heightDp = 844)
@Composable
fun HomeScreenPreview() {
    MaterialTheme {
        HomeScreen()
    }
}