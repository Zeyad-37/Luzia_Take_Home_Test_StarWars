package com.luzia.sharedui.composables

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.luzia.sharedui.theme.LuziaStarWarsTheme

@Composable
fun ErrorScreen(message: String) {
    Text(text = message, Modifier.padding(24.dp), color = Red)
}

@Composable
@Preview(showBackground = true)
fun ErrorScreenPreview() {
    LuziaStarWarsTheme {
        ErrorScreen("Error message")
    }
}
