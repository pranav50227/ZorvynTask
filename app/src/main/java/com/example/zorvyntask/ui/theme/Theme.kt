package com.example.zorvyntask.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import com.example.zorvyntask.ui.Screens.BackgroundGray
import com.example.zorvyntask.ui.Screens.CardWhite
import com.example.zorvyntask.ui.Screens.GreenPrimary
import com.example.zorvyntask.ui.Screens.TextDark


private val AppLightColorScheme = lightColorScheme(
    primary          = GreenPrimary,
    onPrimary        = Color.White,
    primaryContainer = Color(0xFFE8F5EF),
    background       = BackgroundGray,
    surface          = CardWhite,
    onBackground     = TextDark,
    onSurface        = TextDark
)

private val AppDarkColorScheme = darkColorScheme(
    primary          = Color(0xFF4CAF82),
    onPrimary        = Color.Black,
    primaryContainer = Color(0xFF1A3D2E),
    background       = Color(0xFF121212),
    surface          = Color(0xFF1E1E1E),
    onBackground     = Color.White,
    onSurface        = Color.White
)

@Composable
fun ZorvynTaskTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) AppDarkColorScheme else AppLightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        content     = content
    )
}