package com.example.zorvyntask.ui.Screens

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.zorvyntask.data.model.Goals.BadgeItem
import com.example.zorvyntask.data.model.Goals.GoalDetailData
import com.example.zorvyntask.data.model.Goals.GoalProgressData
import com.example.zorvyntask.data.model.Goals.GoalStatItem
import com.example.zorvyntask.data.model.Goals.GoalsScreenData
import com.example.zorvyntask.ui.components.AppCard
import com.example.zorvyntask.ui.components.AppColumn
import com.example.zorvyntask.ui.components.AppRow
import com.example.zorvyntask.ui.components.AppSpacer
import com.example.zorvyntask.ui.components.AppText
import com.example.zorvyntask.ui.components.TextType
import com.example.zorvyntask.ui.theme.SpaceSize

// ─────────────────────────────────────────────────────────────────────────────
// Sample Data Models & Data
// ─────────────────────────────────────────────────────────────────────────────
enum class GoalStatIcon { Streak, Calendar }

enum class BadgeIconType { Shield, Rocket }

// ── Populated sample data ─────────────────────────────────────────────────────

val sampleGoalsScreenData = GoalsScreenData(
    topBarTitle = "Zorvyn Task",
    progress = GoalProgressData(
        progressLabel = "CURRENT PROGRESS",
        progressPercent = 70,
        savedAmount = "\$3,500"
    ),
    goalDetail = GoalDetailData(
        goalName = "Dream Vacation",
        progressPercent = 70,
        remainingAmount = "\$1,500",
        motivationSuffix = "to go until your dream getaway.",
        targetLabel = "Target:",
        targetAmount = "\$5,000"
    ),
    stats = listOf(
        GoalStatItem(
            iconType = GoalStatIcon.Streak,
            value = "15 Days",
            description = "NO OVERSPENDING"
        ),
        GoalStatItem(
            iconType = GoalStatIcon.Calendar,
            value = "Sep 24",
            description = "TARGET DATE"
        )
    ),
    recentBadgesLabel = "RECENT BADGES",
    viewCollectionLabel = "View Collection",
    badges = listOf(
        BadgeItem(
            iconType = BadgeIconType.Shield,
            title = "Budget Master",
            subtitle = "30 day streak"
        ),
        BadgeItem(
            iconType = BadgeIconType.Rocket,
            title = "Early Saver",
            subtitle = "Goal starter"
        )
    ),
    addSavingsLabel = "+ Add Savings"
)

// ─────────────────────────────────────────────────────────────────────────────
// Goals Screen
// ─────────────────────────────────────────────────────────────────────────────

@Composable
fun GoalsScreen(
    data: GoalsScreenData = sampleGoalsScreenData
) {
    Scaffold(
        containerColor = BackgroundGray,
        bottomBar = {
            GoalsBottomCta(label = data.addSavingsLabel)
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item { AppSpacer(SpaceSize.Medium) }

            // Top Bar
            item { GoalsTopBar(title = data.topBarTitle) }
            item { AppSpacer(SpaceSize.Large) }

            // Large circular progress
            item { GoalCircularProgress(data = data.progress) }
            item { AppSpacer(SpaceSize.Large) }

            // Goal detail card
            item { GoalDetailCard(data = data.goalDetail) }
            item { AppSpacer(SpaceSize.Medium) }

            // Stats row
            item { GoalStatsRow(stats = data.stats) }
            item { AppSpacer(SpaceSize.Medium) }

            // Recent Badges
            item { RecentBadgesSection(
                sectionLabel = data.recentBadgesLabel,
                viewAllLabel = data.viewCollectionLabel,
                badges = data.badges
            ) }
            item { AppSpacer(SpaceSize.Large) }
        }
    }
}

// ─────────────────────────────────────────────────────────────────────────────
// Top Bar
// ─────────────────────────────────────────────────────────────────────────────

@Composable
fun GoalsTopBar(title: String) {
    AppRow(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Default.Menu,
            contentDescription = "Menu",
            tint = GreenPrimary,
            modifier = Modifier.size(26.dp)
        )
        AppSpacer(SpaceSize.Small, isHorizontal = true)
        AppText(
            text = title,
            type = TextType.Heading,
            color = GreenPrimary
        )
        Spacer(modifier = Modifier.weight(1f))
        Box(
            modifier = Modifier
                .size(44.dp)
                .clip(CircleShape)
                .background(Color(0xFF4A5568)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = "Profile",
                tint = Color.White,
                modifier = Modifier.size(26.dp)
            )
        }
    }
}

// ─────────────────────────────────────────────────────────────────────────────
// Large Circular Progress
// ─────────────────────────────────────────────────────────────────────────────

@Composable
fun GoalCircularProgress(data: GoalProgressData) {
    Box(
        modifier = Modifier.size(240.dp),
        contentAlignment = Alignment.Center
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val strokeWidth = 18.dp.toPx()
            val inset = strokeWidth / 2f
            val arcSize = Size(size.width - strokeWidth, size.height - strokeWidth)
            val topLeft = Offset(inset, inset)

            // Background track
            drawArc(
                color = Color(0xFFDDE2E8),
                startAngle = -220f,
                sweepAngle = 260f,
                useCenter = false,
                topLeft = topLeft,
                size = arcSize,
                style = Stroke(width = strokeWidth, cap = StrokeCap.Round)
            )

            // Progress arc
            val sweep = 260f * (data.progressPercent / 100f)
            drawArc(
                color = GreenPrimary,
                startAngle = -220f,
                sweepAngle = sweep,
                useCenter = false,
                topLeft = topLeft,
                size = arcSize,
                style = Stroke(width = strokeWidth, cap = StrokeCap.Round)
            )
        }

        // Centre text
        AppColumn(horizontalAlignment = Alignment.CenterHorizontally) {
            AppText(
                text = data.progressLabel,
                type = TextType.Body,
                color = TextGray,
                textAlign = TextAlign.Center
            )
            AppSpacer(SpaceSize.Small)
            Text(
                text = "${data.progressPercent}%",
                fontSize = 48.sp,
                fontWeight = FontWeight.ExtraBold,
                color = TextDark,
                textAlign = TextAlign.Center
            )
            AppText(
                text = data.savedAmount,
                type = TextType.SubHeading,
                color = GreenPrimary,
                textAlign = TextAlign.Center
            )
        }
    }
}

// ─────────────────────────────────────────────────────────────────────────────
// Goal Detail Card
// ─────────────────────────────────────────────────────────────────────────────

@Composable
fun GoalDetailCard(data: GoalDetailData) {
    AppCard(modifier = Modifier.fillMaxWidth()) {
        AppColumn(
            modifier = Modifier.padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AppText(
                text = data.goalName,
                type = TextType.Heading,
                color = TextDark,
                textAlign = TextAlign.Center
            )
            AppSpacer(SpaceSize.Small)

            // "You're 70% there! Just $1,500 to go until your dream getaway."
            val annotated = buildAnnotatedString {
                append("You're ${data.progressPercent}% there! Just ")
                withStyle(SpanStyle(color = GreenPrimary, fontWeight = FontWeight.Bold)) {
                    append(data.remainingAmount)
                }
                append(" ${data.motivationSuffix}")
            }
            Text(
                text = annotated,
                fontSize = 14.sp,
                color = TextGray,
                textAlign = TextAlign.Center,
                lineHeight = 20.sp
            )

            AppSpacer(SpaceSize.Medium)
            Divider(color = Color(0xFFEEEEEE), thickness = 1.dp)
            AppSpacer(SpaceSize.Medium)

            AppText(
                text = "${data.targetLabel} ${data.targetAmount}",
                type = TextType.Body,
                color = TextGray,
                textAlign = TextAlign.Center
            )
        }
    }
}

// ─────────────────────────────────────────────────────────────────────────────
// Stats Row  (2 equal cards side-by-side)
// ─────────────────────────────────────────────────────────────────────────────

@Composable
fun GoalStatsRow(stats: List<GoalStatItem>) {
    AppRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        stats.forEach { stat ->
            GoalStatCard(
                stat = stat,
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
fun GoalStatCard(stat: GoalStatItem, modifier: Modifier = Modifier) {
    AppCard(modifier = modifier) {
        AppColumn(modifier = Modifier.padding(16.dp)) {
            // Icon
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color(0xFFE8F5EF)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = when (stat.iconType) {
                        GoalStatIcon.Streak   -> Icons.Default.LocalFireDepartment
                        GoalStatIcon.Calendar -> Icons.Default.CalendarToday
                    },
                    contentDescription = stat.description,
                    tint = GreenPrimary,
                    modifier = Modifier.size(22.dp)
                )
            }
            AppSpacer(SpaceSize.Medium)
            Text(
                text = stat.value,
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold,
                color = TextDark
            )
            AppSpacer(SpaceSize.Small)
            AppText(
                text = stat.description,
                type = TextType.Body,
                color = TextGray
            )
        }
    }
}

// ─────────────────────────────────────────────────────────────────────────────
// Recent Badges Section
// ─────────────────────────────────────────────────────────────────────────────

@Composable
fun RecentBadgesSection(
    sectionLabel: String,
    viewAllLabel: String,
    badges: List<BadgeItem>
) {
    AppColumn(modifier = Modifier.fillMaxWidth()) {
        // Header row
        AppRow(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AppText(
                text = sectionLabel,
                type = TextType.Body,
                color = TextGray,
                modifier = Modifier.weight(1f)
            )
            AppText(
                text = viewAllLabel,
                type = TextType.Body,
                color = GreenPrimary
            )
        }
        AppSpacer(SpaceSize.Medium)

        // Badge cards grid (2 per row)
        AppRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            badges.forEach { badge ->
                BadgeCard(
                    badge = badge,
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}

@Composable
fun BadgeCard(badge: BadgeItem, modifier: Modifier = Modifier) {
    AppCard(modifier = modifier) {
        AppColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Badge icon circle
            val (bgColor, iconColor) = when (badge.iconType) {
                BadgeIconType.Shield -> Color(0xFFE8F5EF) to GreenPrimary
                BadgeIconType.Rocket -> Color(0xFFFFF3E0) to Color(0xFFFF8F00)
            }
            Box(
                modifier = Modifier
                    .size(64.dp)
                    .clip(CircleShape)
                    .background(bgColor),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = when (badge.iconType) {
                        BadgeIconType.Shield -> Icons.Default.VerifiedUser
                        BadgeIconType.Rocket -> Icons.Default.RocketLaunch
                    },
                    contentDescription = badge.title,
                    tint = iconColor,
                    modifier = Modifier.size(30.dp)
                )
            }
            AppSpacer(SpaceSize.Small)
            AppText(
                text = badge.title,
                type = TextType.Body,
                color = TextDark,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
            AppSpacer(SpaceSize.Small)
            AppText(
                text = badge.subtitle,
                type = TextType.Body,
                color = TextGray,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

// ─────────────────────────────────────────────────────────────────────────────
// Bottom CTA Button
// ─────────────────────────────────────────────────────────────────────────────

@Composable
fun GoalsBottomCta(label: String) {
    Surface(color = BackgroundGray) {
        Button(
            onClick = {},
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 16.dp)
                .height(56.dp),
            shape = RoundedCornerShape(28.dp),
            colors = ButtonDefaults.buttonColors(containerColor = GreenPrimary)
        ) {
            AppText(
                text = label,
                type = TextType.SubHeading,
                color = Color.White
            )
        }
    }
}

// ─────────────────────────────────────────────────────────────────────────────
// Preview
// ─────────────────────────────────────────────────────────────────────────────

@Preview(showBackground = true, widthDp = 390, heightDp = 844)
@Composable
fun GoalsScreenPreview() {
    MaterialTheme {
        GoalsScreen()
    }
}