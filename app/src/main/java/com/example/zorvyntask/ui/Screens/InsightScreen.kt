package com.example.zorvyntask.ui.Screens

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PlayCircle
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material.icons.filled.TrendingUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.zorvyntask.data.model.Insight.CategoryBreakdownData
import com.example.zorvyntask.data.model.Insight.CategoryBreakdownItem
import com.example.zorvyntask.data.model.Insight.FrequentSubscriptionData
import com.example.zorvyntask.data.model.Insight.HighestSpendingData
import com.example.zorvyntask.data.model.Insight.InsightTipData
import com.example.zorvyntask.data.model.Insight.MonthlyTrendData
import com.example.zorvyntask.data.model.Insight.MonthlyTrendPoint
import com.example.zorvyntask.data.model.Insight.WeeklyComparisonData
import com.example.zorvyntask.ui.components.AppCard
import com.example.zorvyntask.ui.components.AppColumn
import com.example.zorvyntask.ui.components.AppRow
import com.example.zorvyntask.ui.components.AppSpacer
import com.example.zorvyntask.ui.components.AppText
import com.example.zorvyntask.ui.components.TextType
import com.example.zorvyntask.ui.theme.SpaceSize

// ─────────────────────────────────────────────────────────────────────────────
// Sample Data
// ─────────────────────────────────────────────────────────────────────────────

// ── Populated sample data ─────────────────────────────────────────────────────

val sampleHighestSpending = HighestSpendingData(
    category = "Dining",
    amount = "\$1,420.50",
    changePercent = 12,
    vsLabel = "vs last month"
)

val sampleMonthlyTrend = MonthlyTrendData(
    points = listOf(
        MonthlyTrendPoint("JAN", 2100f),
        MonthlyTrendPoint("FEB", 1800f),
        MonthlyTrendPoint("MAR", 2600f),
        MonthlyTrendPoint("APR", 1600f),
        MonthlyTrendPoint("MAY", 3240f),
        MonthlyTrendPoint("JUN", 2900f),
    ),
    highlightMonth = "MAY",
    highlightLabel = "May: \$3,240",
    selectedPeriod = "6 Months",
    periods = listOf("6 Months", "1 Year")
)

val sampleCategoryBreakdown = CategoryBreakdownData(
    total = "\$4.8k",
    items = listOf(
        CategoryBreakdownItem("Rent", 45f, Color(0xFF1A8A5A)),
        CategoryBreakdownItem("Food", 25f, Color(0xFF4CAF82)),
        CategoryBreakdownItem("Utilities", 20f, Color(0xFFB0BEC5)),
        CategoryBreakdownItem("Fun", 10f, Color(0xFFD32F2F)),
    )
)

val sampleWeeklyComparison = WeeklyComparisonData(
    thisWeekLabel = "THIS WEEK",
    thisWeekAmount = "\$842.00",
    thisWeekProgress = 0.75f,
    lastWeekLabel = "LAST WEEK",
    lastWeekAmount = "\$1,120.00",
    lastWeekProgress = 1f,
    savingPercent = 24,
    projectedSaving = "\$320.00"
)

val sampleSubscription = FrequentSubscriptionData(
    badgeLabel = "MOST FREQUENT",
    count = "12x",
    totalLabel = "Total Monthly",
    totalAmount = "\$128.40"
)

val sampleInsightTip = InsightTipData(
    title = "Atelier Insight",
    description = "Your utility costs peaked in March. Consider an energy audit."
)

// ─────────────────────────────────────────────────────────────────────────────
// Insight Screen
// ─────────────────────────────────────────────────────────────────────────────

@Composable
fun InsightScreen(
    highestSpending: HighestSpendingData = sampleHighestSpending,
    monthlyTrend: MonthlyTrendData = sampleMonthlyTrend,
    categoryBreakdown: CategoryBreakdownData = sampleCategoryBreakdown,
    weeklyComparison: WeeklyComparisonData = sampleWeeklyComparison,
    subscription: FrequentSubscriptionData = sampleSubscription,
    insightTip: InsightTipData = sampleInsightTip
) {
    Scaffold(containerColor = BackgroundGray) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(0.dp)
        ) {
            item { AppSpacer(SpaceSize.Medium) }
            item { InsightTopBar() }
            item { AppSpacer(SpaceSize.Medium) }

            // 1. Highest Spending
            item { HighestSpendingCard(data = highestSpending) }
            item { AppSpacer(SpaceSize.Medium) }

            // 2. Monthly Trend
            item { MonthlyTrendCard(data = monthlyTrend) }
            item { AppSpacer(SpaceSize.Medium) }

            // 3. Category Breakdown
            item { CategoryBreakdownCard(data = categoryBreakdown) }
            item { AppSpacer(SpaceSize.Medium) }

            // 4. Weekly Comparison
            item { WeeklyComparisonCard(data = weeklyComparison) }
            item { AppSpacer(SpaceSize.Medium) }

            // 5. Frequent Subscription
            item { FrequentSubscriptionCard(data = subscription) }
            item { AppSpacer(SpaceSize.Medium) }

            // 6. Insight Tip + CTA
            item { InsightTipCard(data = insightTip) }
            item { AppSpacer(SpaceSize.Medium) }
        }
    }
}

// ─────────────────────────────────────────────────────────────────────────────
// Top Bar
// ─────────────────────────────────────────────────────────────────────────────

@Composable
fun InsightTopBar() {
    AppRow(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(Color(0xFF4DB6AC)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = "Avatar",
                tint = Color.White,
                modifier = Modifier.size(24.dp)
            )
        }
        AppSpacer(SpaceSize.Small, isHorizontal = true)
        AppText(
            text = "Insights",
            type = TextType.Heading,
            color = GreenPrimary
        )
        Spacer(modifier = Modifier.weight(1f))
        Icon(
            imageVector = Icons.Default.Notifications,
            contentDescription = "Notifications",
            tint = TextDark,
            modifier = Modifier.size(24.dp)
        )
    }
}

// ─────────────────────────────────────────────────────────────────────────────
// 1. Highest Spending Card
// ─────────────────────────────────────────────────────────────────────────────

@Composable
fun HighestSpendingCard(data: HighestSpendingData) {
    AppCard(modifier = Modifier.fillMaxWidth()) {
        AppRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.Top
        ) {
            AppColumn(modifier = Modifier.weight(1f)) {
                AppText(
                    text = "HIGHEST SPENDING",
                    type = TextType.Body,
                    color = TextGray
                )
                AppSpacer(SpaceSize.Small)
                AppText(
                    text = data.category,
                    type = TextType.Heading,
                    color = TextDark
                )
                AppSpacer(SpaceSize.Small)
                AppText(
                    text = data.amount,
                    type = TextType.Heading,
                    color = RedAccent,
                    modifier = Modifier.wrapContentWidth()
                )
                AppSpacer(SpaceSize.Small)
                AppRow(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.TrendingUp,
                        contentDescription = "Trend",
                        tint = RedAccent,
                        modifier = Modifier.size(16.dp)
                    )
                    AppSpacer(SpaceSize.Small, isHorizontal = true)
                    AppText(
                        text = "+${data.changePercent}%  ${data.vsLabel}",
                        type = TextType.Body,
                        color = TextGray
                    )
                }
            }

            // Icon Badge
            Box(
                modifier = Modifier
                    .size(52.dp)
                    .clip(RoundedCornerShape(14.dp))
                    .background(Color(0xFFFCE8E8)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Restaurant,
                    contentDescription = data.category,
                    tint = RedAccent,
                    modifier = Modifier.size(26.dp)
                )
            }
        }
    }
}

// ─────────────────────────────────────────────────────────────────────────────
// 2. Monthly Trend Card
// ─────────────────────────────────────────────────────────────────────────────

@Composable
fun MonthlyTrendCard(data: MonthlyTrendData) {
    var selectedPeriod by remember { mutableStateOf(data.selectedPeriod) }

    AppCard(modifier = Modifier.fillMaxWidth()) {
        AppColumn(modifier = Modifier.padding(16.dp)) {

            // Header row
            AppRow(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                AppText(
                    text = "Monthly Trend",
                    type = TextType.SubHeading,
                    color = TextDark,
                    modifier = Modifier.weight(1f)
                )
                data.periods.forEach { period ->
                    val isSelected = period == selectedPeriod
                    Surface(
                        shape = RoundedCornerShape(20.dp),
                        color = if (isSelected) TextDark else Color.Transparent,
                        modifier = Modifier
                            .clip(RoundedCornerShape(20.dp))
                    ) {
                        AppText(
                            text = period,
                            type = TextType.Body,
                            color = if (isSelected) Color.White else TextGray,
                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
                        )
                    }
                    if (period != data.periods.last()) {
                        AppSpacer(SpaceSize.Small, isHorizontal = true)
                    }
                }
            }

            AppSpacer(SpaceSize.Medium)

            // Line chart
            LineChartCanvas(
                points = data.points,
                highlightMonth = data.highlightMonth,
                highlightLabel = data.highlightLabel,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(140.dp)
            )

            AppSpacer(SpaceSize.Small)

            // X-axis labels
            AppRow(modifier = Modifier.fillMaxWidth()) {
                data.points.forEach { point ->
                    AppText(
                        text = point.month,
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
fun LineChartCanvas(
    points: List<MonthlyTrendPoint>,
    highlightMonth: String,
    highlightLabel: String,
    modifier: Modifier = Modifier
) {
    val green = GreenPrimary
    val greenLight = Color(0xFFE8F5EF)
    val white = Color.White

    Canvas(modifier = modifier) {
        val w = size.width
        val h = size.height
        val minVal = points.minOf { it.value }
        val maxVal = points.maxOf { it.value }
        val range = (maxVal - minVal).coerceAtLeast(1f)
        val count = points.size

        fun xAt(i: Int) = w * i / (count - 1).toFloat()
        fun yAt(v: Float) = h * 0.85f - ((v - minVal) / range) * (h * 0.70f)

        // Build smooth path using cubic bezier
        val linePath = Path()
        val fillPath = Path()

        linePath.moveTo(xAt(0), yAt(points[0].value))
        fillPath.moveTo(xAt(0), h)
        fillPath.lineTo(xAt(0), yAt(points[0].value))

        for (i in 1 until count) {
            val prevX = xAt(i - 1)
            val prevY = yAt(points[i - 1].value)
            val currX = xAt(i)
            val currY = yAt(points[i].value)
            val cpX = (prevX + currX) / 2f
            linePath.cubicTo(cpX, prevY, cpX, currY, currX, currY)
            fillPath.cubicTo(cpX, prevY, cpX, currY, currX, currY)
        }

        fillPath.lineTo(xAt(count - 1), h)
        fillPath.close()

        // Fill gradient
        drawPath(
            path = fillPath,
            brush = Brush.verticalGradient(
                colors = listOf(greenLight, Color.Transparent),
                startY = 0f,
                endY = h
            )
        )

        // Line
        drawPath(
            path = linePath,
            color = green,
            style = Stroke(width = 3.dp.toPx(), cap = StrokeCap.Round, join = StrokeJoin.Round)
        )

        // Highlight dot & tooltip
        val hiIdx = points.indexOfFirst { it.month == highlightMonth }
        if (hiIdx >= 0) {
            val hx = xAt(hiIdx)
            val hy = yAt(points[hiIdx].value)

            // Outer white ring
            drawCircle(color = white, radius = 8.dp.toPx(), center = Offset(hx, hy))
            drawCircle(color = green, radius = 5.dp.toPx(), center = Offset(hx, hy))

            // Tooltip pill
            val pillW = 100.dp.toPx()
            val pillH = 28.dp.toPx()
            val pillLeft = (hx - pillW / 2).coerceIn(0f, w - pillW)
            val pillTop = hy - pillH - 12.dp.toPx()

            drawRoundRect(
                color = green,
                topLeft = Offset(pillLeft, pillTop),
                size = Size(pillW, pillH),
                cornerRadius = androidx.compose.ui.geometry.CornerRadius(14.dp.toPx())
            )
        }
    }

    // Overlay text for tooltip (Canvas can't render text well)
    val hiIdx = points.indexOfFirst { it.month == highlightMonth }
    if (hiIdx >= 0) {
        Box(modifier = modifier) {
            AppText(
                text = highlightLabel,
                type = TextType.Body,
                color = Color.White,
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .offset(
                        x = ((hiIdx - (points.size - 1) / 2f) * 30).dp,
                        y = 4.dp
                    )
            )
        }
    }
}

// ─────────────────────────────────────────────────────────────────────────────
// 3. Category Breakdown Card
// ─────────────────────────────────────────────────────────────────────────────

@Composable
fun CategoryBreakdownCard(data: CategoryBreakdownData) {
    AppCard(modifier = Modifier.fillMaxWidth()) {
        AppColumn(modifier = Modifier.padding(16.dp)) {
            AppText(
                text = "Category Breakdown",
                type = TextType.SubHeading,
                color = TextDark
            )

            AppSpacer(SpaceSize.Medium)

            // Donut chart centred
            Box(
                modifier = Modifier
                    .size(160.dp)
                    .align(Alignment.CenterHorizontally),
                contentAlignment = Alignment.Center
            ) {
                DonutChart(items = data.items)
                AppColumn(horizontalAlignment = Alignment.CenterHorizontally) {
                    AppText(
                        text = "Total",
                        type = TextType.Body,
                        color = TextGray
                    )
                    AppText(
                        text = data.total,
                        type = TextType.SubHeading,
                        color = TextDark
                    )
                }
            }

            AppSpacer(SpaceSize.Medium)

            // Legend
            data.items.forEach { item ->
                AppRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(10.dp)
                            .clip(CircleShape)
                            .background(item.color)
                    )
                    AppSpacer(SpaceSize.Small, isHorizontal = true)
                    AppText(
                        text = item.label,
                        type = TextType.Body,
                        color = TextDark,
                        modifier = Modifier.weight(1f)
                    )
                    AppText(
                        text = "${item.percentage.toInt()}%",
                        type = TextType.Body,
                        color = TextDark
                    )
                }
            }
        }
    }
}

@Composable
fun DonutChart(items: List<CategoryBreakdownItem>) {
    Canvas(modifier = Modifier.fillMaxSize()) {
        val strokeWidth = 22.dp.toPx()
        val inset = strokeWidth / 2
        val arcSize = Size(size.width - strokeWidth, size.height - strokeWidth)
        val topLeft = Offset(inset, inset)
        var startAngle = -90f

        items.forEach { item ->
            val sweep = item.percentage / 100f * 360f
            drawArc(
                color = item.color,
                startAngle = startAngle,
                sweepAngle = sweep - 2f,  // small gap between segments
                useCenter = false,
                topLeft = topLeft,
                size = arcSize,
                style = Stroke(width = strokeWidth, cap = StrokeCap.Butt)
            )
            startAngle += sweep
        }
    }
}

// ─────────────────────────────────────────────────────────────────────────────
// 4. Weekly Comparison Card
// ─────────────────────────────────────────────────────────────────────────────

@Composable
fun WeeklyComparisonCard(data: WeeklyComparisonData) {
    AppCard(modifier = Modifier.fillMaxWidth()) {
        AppColumn(modifier = Modifier.padding(16.dp)) {
            AppText(
                text = "Weekly Comparison",
                type = TextType.SubHeading,
                color = TextDark
            )

            AppSpacer(SpaceSize.Medium)

            // This week
            WeekBarRow(
                label = data.thisWeekLabel,
                amount = data.thisWeekAmount,
                progress = data.thisWeekProgress,
                barColor = GreenPrimary
            )

            AppSpacer(SpaceSize.Medium)

            // Last week
            WeekBarRow(
                label = data.lastWeekLabel,
                amount = data.lastWeekAmount,
                progress = data.lastWeekProgress,
                barColor = Color(0xFFB0BEC5)
            )

            AppSpacer(SpaceSize.Medium)

            // Savings tip banner
            Surface(
                shape = RoundedCornerShape(10.dp),
                color = Color(0xFFF0F8F4)
            ) {
                val annotated = buildAnnotatedString {
                    append("Good work! ")
                    append("You spent ${data.savingPercent}% less than last week. Your projected monthly saving is ")
                    withStyle(SpanStyle(fontWeight = FontWeight.Bold, color = TextDark)) {
                        append(data.projectedSaving)
                    }
                    append(".")
                }
                Text(
                    text = annotated,
                    modifier = Modifier.padding(12.dp),
                    fontSize = 13.sp,
                    color = TextGray,
                    lineHeight = 18.sp
                )
            }
        }
    }
}

@Composable
fun WeekBarRow(
    label: String,
    amount: String,
    progress: Float,
    barColor: Color
) {
    val animatedProgress by animateFloatAsState(
        targetValue = progress,
        animationSpec = tween(durationMillis = 800),
        label = "barProgress"
    )

    AppColumn {
        AppRow(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AppText(
                text = label,
                type = TextType.Body,
                color = TextGray,
                modifier = Modifier.weight(1f)
            )
            AppText(
                text = amount,
                type = TextType.Body,
                color = TextDark
            )
        }
        AppSpacer(SpaceSize.Small)
        LinearProgressIndicator(
            progress = { animatedProgress },
            modifier = Modifier
                .fillMaxWidth()
                .height(8.dp)
                .clip(RoundedCornerShape(4.dp)),
            color = barColor,
            trackColor = Color(0xFFE0E0E0)
        )
    }
}

// ─────────────────────────────────────────────────────────────────────────────
// 5. Frequent Subscription Card
// ─────────────────────────────────────────────────────────────────────────────

@Composable
fun FrequentSubscriptionCard(data: FrequentSubscriptionData) {
    AppCard(modifier = Modifier.fillMaxWidth()) {
        AppColumn(modifier = Modifier.padding(16.dp)) {
            AppText(
                text = data.badgeLabel,
                type = TextType.Body,
                color = TextGray
            )
            AppSpacer(SpaceSize.Small)
            AppText(
                text = "Subscriptions",
                type = TextType.Heading,
                color = TextDark
            )

            AppSpacer(SpaceSize.Medium)

            // Subscription icon with count badge
            Box(modifier = Modifier.size(72.dp)) {
                Box(
                    modifier = Modifier
                        .size(64.dp)
                        .clip(RoundedCornerShape(18.dp))
                        .background(GreenPrimary),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.PlayCircle,
                        contentDescription = "Subscription",
                        tint = Color.White,
                        modifier = Modifier.size(34.dp)
                    )
                }
                // Count badge
                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = Color(0xFF2E7D52),
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .wrapContentSize()
                ) {
                    AppText(
                        text = data.count,
                        type = TextType.Body,
                        color = Color.White,
                        modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                    )
                }
            }

            AppSpacer(SpaceSize.Medium)

            AppRow(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                AppColumn(modifier = Modifier.weight(1f)) {
                    AppText(
                        text = data.totalLabel,
                        type = TextType.Body,
                        color = TextGray
                    )
                    AppSpacer(SpaceSize.Small)
                    AppText(
                        text = data.totalAmount,
                        type = TextType.SubHeading,
                        color = TextDark
                    )
                }
                Icon(
                    imageVector = Icons.Default.ArrowForward,
                    contentDescription = "Details",
                    tint = TextDark,
                    modifier = Modifier.size(22.dp)
                )
            }
        }
    }
}

// ─────────────────────────────────────────────────────────────────────────────
// 6. Insight Tip Card + CTA Button
// ─────────────────────────────────────────────────────────────────────────────

@Composable
fun InsightTipCard(data: InsightTipData) {
    AppCard(modifier = Modifier.fillMaxWidth()) {
        AppColumn(modifier = Modifier.padding(16.dp)) {
            AppRow(verticalAlignment = Alignment.CenterVertically) {
                // Lightbulb icon
                Box(
                    modifier = Modifier
                        .size(44.dp)
                        .clip(CircleShape)
                        .background(BackgroundGray),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Lightbulb,
                        contentDescription = "Tip",
                        tint = GreenPrimary,
                        modifier = Modifier.size(24.dp)
                    )
                }
                AppSpacer(SpaceSize.Small, isHorizontal = true)
                AppColumn {
                    AppText(
                        text = data.title,
                        type = TextType.SubHeading,
                        color = TextDark
                    )
                    AppSpacer(SpaceSize.Small)
                    AppText(
                        text = data.description,
                        type = TextType.Body,
                        color = TextGray,
                        maxLines = 3
                    )
                }
            }

            AppSpacer(SpaceSize.Medium)

            // CTA Button
            Button(
                onClick = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
                shape = RoundedCornerShape(26.dp),
                colors = ButtonDefaults.buttonColors(containerColor = GreenPrimary)
            ) {
                AppText(
                    text = "Apply Optimization",
                    type = TextType.SubHeading,
                    color = Color.White
                )
            }
        }
    }
}

// ─────────────────────────────────────────────────────────────────────────────
// Preview
// ─────────────────────────────────────────────────────────────────────────────

@Preview(showBackground = true, widthDp = 390, heightDp = 844)
@Composable
fun InsightScreenPreview() {
    MaterialTheme {
        InsightScreen()
    }
}