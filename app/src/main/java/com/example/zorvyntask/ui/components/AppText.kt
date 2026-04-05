package com.example.zorvyntask.ui.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

enum class TextType {
    Heading,
    SubHeading,
    Body
}

@Composable
fun AppText(
    text: String,
    modifier: Modifier = Modifier,
    type: TextType = TextType.Body,
    color: Color = Color.Black,
    textAlign: TextAlign? = null,
    maxLines: Int = Int.MAX_VALUE
) {
    val (fontSize, fontWeight) = when (type) {
        TextType.Heading    -> 20.sp to androidx.compose.ui.text.font.FontWeight.Bold
        TextType.SubHeading -> 16.sp to androidx.compose.ui.text.font.FontWeight.SemiBold
        TextType.Body       -> 14.sp to androidx.compose.ui.text.font.FontWeight.Normal
    }
    Text(
        text = text,
        modifier = modifier,
        fontSize = fontSize,
        fontWeight = fontWeight,
        color = color,
        textAlign = textAlign,
        maxLines = maxLines
    )
}

@Preview(showBackground = true)
@Composable
fun appTextPrev(){
    AppText("Title", type = TextType.Heading)
}