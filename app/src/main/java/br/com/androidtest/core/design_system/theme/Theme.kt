package br.com.androidtest.core.design_system.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun AppTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = lightColorScheme(
            primary = Color.Black,
            background = Color.White,
            surface = Color.White,
            surfaceVariant = Color.White
        ),
        content = content
    )
}