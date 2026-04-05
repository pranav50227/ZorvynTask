package com.example.zorvyntask

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.ReceiptLong
import androidx.compose.material.icons.filled.Savings
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.zorvyntask.ui.Screens.BackgroundGray
import com.example.zorvyntask.ui.Screens.CardWhite
import com.example.zorvyntask.ui.Screens.GoalsScreen
import com.example.zorvyntask.ui.Screens.GreenPrimary
import com.example.zorvyntask.ui.Screens.HomeScreen
import com.example.zorvyntask.ui.Screens.InsightScreen
import com.example.zorvyntask.ui.Screens.TextGray
import com.example.zorvyntask.ui.Screens.TransactionScreen
import com.example.zorvyntask.ui.components.AppText
import com.example.zorvyntask.ui.components.TextType

// ─────────────────────────────────────────────────────────────────────────────
// Route constants  –  single source of truth, no magic strings anywhere else
// ─────────────────────────────────────────────────────────────────────────────

object AppRoutes {
    const val HOME         = "home"
    const val TRANSACTIONS = "transactions"
    const val SAVINGS      = "savings"
    const val INSIGHT      = "insight"
}

// ─────────────────────────────────────────────────────────────────────────────
// Nav destination model
// ─────────────────────────────────────────────────────────────────────────────

data class AppNavDestination(
    val route: String,
    val label: String,
    val icon: ImageVector,
    val contentDescription: String
)

// ─────────────────────────────────────────────────────────────────────────────
// All tabs — order here drives the order in the bar
// ─────────────────────────────────────────────────────────────────────────────

private val navDestinations = listOf(
    AppNavDestination(
        route              = AppRoutes.HOME,
        label              = "HOME",
        icon               = Icons.Default.Home,
        contentDescription = "Home screen"
    ),
    AppNavDestination(
        route              = AppRoutes.TRANSACTIONS,
        label              = "TRANSACTIONS",
        icon               = Icons.Default.ReceiptLong,
        contentDescription = "Transactions screen"
    ),
    AppNavDestination(
        route              = AppRoutes.SAVINGS,
        label              = "SAVINGS",
        icon               = Icons.Default.Savings,
        contentDescription = "Savings and goals screen"
    ),
    AppNavDestination(
        route              = AppRoutes.INSIGHT,
        label              = "INSIGHT",
        icon               = Icons.Default.Lightbulb,
        contentDescription = "Insights screen"
    )
)

// ─────────────────────────────────────────────────────────────────────────────
// AppNavController  –  plain state holder; no Jetpack NavController needed
// for a flat tab structure. Kept in a separate class so it can be hoisted,
// tested, or replaced later without touching the UI.
// ─────────────────────────────────────────────────────────────────────────────

class AppNavController(initialRoute: String = AppRoutes.HOME) {

    var currentRoute by mutableStateOf(initialRoute)
        private set

    /** Navigate to [route]. Ignored if already on that route (no-op = safe). */
    fun navigateTo(route: String) {
        if (route != currentRoute && navDestinations.any { it.route == route }) {
            currentRoute = route
        }
    }

    /** Returns the index of the current route in [navDestinations], or 0. */
    fun currentIndex(): Int =
        navDestinations.indexOfFirst { it.route == currentRoute }.coerceAtLeast(0)
}

/** Hoisted creation so the controller survives recomposition. */
@Composable
fun rememberAppNavController(
    initialRoute: String = AppRoutes.HOME
): AppNavController = remember { AppNavController(initialRoute) }

// ─────────────────────────────────────────────────────────────────────────────
// AtelierFinanceApp  –  root shell
// ─────────────────────────────────────────────────────────────────────────────

@Composable
fun ZorvynApp() {
    val navController = rememberAppNavController()

    Scaffold(
        containerColor = BackgroundGray,
        bottomBar = {
            AtelierBottomBar(
                destinations    = navDestinations,
                currentRoute    = navController.currentRoute,
                onTabSelected   = { navController.navigateTo(it.route) }
            )
        }
    ) { innerPadding ->
        // AnimatedContent swaps screens with a smooth crossfade.
        // The key is the route string; Compose tears down the old screen
        // and builds the new one only when the route actually changes.
        AnimatedContent(
            targetState   = navController.currentRoute,
            modifier      = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            transitionSpec = {
                val enter = fadeIn(animationSpec = tween(220)) +
                        slideInHorizontally(
                            animationSpec = tween(220),
                            initialOffsetX = { fullWidth ->
                                // Slide from right if going forward, left if going back
                                val fromIdx = navDestinations.indexOfFirst { it.route == initialState }
                                val toIdx   = navDestinations.indexOfFirst { it.route == targetState }
                                if (toIdx >= fromIdx) fullWidth / 8 else -fullWidth / 8
                            }
                        )
                val exit = fadeOut(animationSpec = tween(180)) +
                        slideOutHorizontally(
                            animationSpec = tween(180),
                            targetOffsetX = { fullWidth ->
                                val fromIdx = navDestinations.indexOfFirst { it.route == initialState }
                                val toIdx   = navDestinations.indexOfFirst { it.route == targetState }
                                if (toIdx >= fromIdx) -fullWidth / 8 else fullWidth / 8
                            }
                        )
                enter togetherWith exit
            },
            label = "screen_transition"
        ) { route ->
            AppScreenHost(route = route)
        }
    }
}

// ─────────────────────────────────────────────────────────────────────────────
// Screen host  –  maps route → composable.
// Isolated here so AtelierFinanceApp stays layout-only.
// ─────────────────────────────────────────────────────────────────────────────

@Composable
private fun AppScreenHost(route: String) {
    when (route) {
        AppRoutes.HOME         -> HomeScreen()
        AppRoutes.TRANSACTIONS -> TransactionScreen()
        AppRoutes.SAVINGS      -> GoalsScreen()
        AppRoutes.INSIGHT      -> InsightScreen()
        // Fallback — should never be reached given navigateTo validation,
        // but guards against future route mismatches.
        else -> UnknownRouteScreen(route)
    }
}

// ─────────────────────────────────────────────────────────────────────────────
// Fallback screen (safety net)
// ─────────────────────────────────────────────────────────────────────────────

@Composable
private fun UnknownRouteScreen(route: String) {
    Box(
        modifier         = Modifier
            .fillMaxSize()
            .background(BackgroundGray),
        contentAlignment = Alignment.Center
    ) {
        AppText(
            text  = "Unknown screen: \"$route\"",
            type  = TextType.Body,
            color = TextGray
        )
    }
}

// ─────────────────────────────────────────────────────────────────────────────
// AtelierBottomBar  –  pure UI, no state ownership
// ─────────────────────────────────────────────────────────────────────────────

@Composable
fun AtelierBottomBar(
    destinations: List<AppNavDestination>,
    currentRoute: String,
    onTabSelected: (AppNavDestination) -> Unit
) {
    NavigationBar(
        containerColor = CardWhite,
        tonalElevation = 8.dp
    ) {
        destinations.forEach { destination ->
            val isSelected = destination.route == currentRoute

            NavigationBarItem(
                selected = isSelected,
                onClick  = { onTabSelected(destination) },
                icon     = {
                    AnimatedContent(
                        targetState   = isSelected,
                        transitionSpec = {
                            fadeIn(tween(200)) togetherWith fadeOut(tween(200))
                        },
                        label = "tab_icon_${destination.route}"
                    ) { selected ->
                        if (selected) {
                            // Active: filled green pill
                            Box(
                                modifier         = Modifier
                                    .size(46.dp)
                                    .clip(CircleShape)
                                    .background(GreenPrimary),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector        = destination.icon,
                                    contentDescription = destination.contentDescription,
                                    tint               = Color.White,
                                    modifier           = Modifier.size(20.dp)
                                )
                            }
                        } else {
                            // Inactive: plain icon
                            Icon(
                                imageVector        = destination.icon,
                                contentDescription = destination.contentDescription,
                                tint               = TextGray,
                                modifier           = Modifier.size(22.dp)
                            )
                        }
                    }
                },
                label = {
                    AppText(
                        text  = destination.label,
                        type  = TextType.Body,
                        color = if (isSelected) GreenPrimary else TextGray
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = Color.Transparent   // remove default ripple tint
                )
            )
        }
    }
}

// ─────────────────────────────────────────────────────────────────────────────
// Preview
// ─────────────────────────────────────────────────────────────────────────────

@Preview(showBackground = true)
@Composable
private fun ZorvynAppPreview() {
    MaterialTheme {
        ZorvynApp()
    }
}