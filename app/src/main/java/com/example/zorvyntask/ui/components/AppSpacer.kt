package com.example.zorvyntask.ui.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.zorvyntask.ui.theme.SpaceSize

@Composable
fun AppSpacer(size: SpaceSize, isHorizontal: Boolean = false) {
    val space = when (size) {
        SpaceSize.Small -> 8.dp
        SpaceSize.Medium -> 16.dp
        SpaceSize.Large -> 24.dp
        else -> 8.dp
    }
    Spacer(
        modifier = if (isHorizontal) Modifier.width(space) else Modifier.height(space)
    )
}


@Preview(showBackground = true)
@Composable
fun appSpacerPrev(){
    AppColumn() {
        AppText("Title", type = TextType.Heading)
        AppSpacer(SpaceSize.Small)
        AppText("Title", type = TextType.Heading)
    }
}