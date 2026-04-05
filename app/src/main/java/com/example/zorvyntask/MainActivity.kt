package com.example.zorvyntask

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.zorvyntask.ui.Screens.BackgroundGray
import com.example.zorvyntask.ui.Screens.CardWhite
import com.example.zorvyntask.ui.Screens.GreenPrimary
import com.example.zorvyntask.ui.Screens.HomeScreen
import com.example.zorvyntask.ui.Screens.TextDark
import com.example.zorvyntask.ui.theme.ZorvynTaskTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ZorvynTaskTheme {
                ZorvynApp()
            }
        }
    }
}
