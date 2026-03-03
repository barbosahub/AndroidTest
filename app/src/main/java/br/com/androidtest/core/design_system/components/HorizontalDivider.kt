package br.com.androidtest.core.design_system.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import br.com.androidtest.R
import br.com.androidtest.core.design_system.theme.AppTheme
import br.com.androidtest.core.design_system.theme.Dimensions

@Preview
@Composable
fun HorizontalDividerPreview() {
    AppTheme {
        Box(modifier = Modifier.background(colorResource(R.color.bgPrimary))) {
            HorizontalDivider()
        }
    }
}

@Composable
fun HorizontalDivider(
    modifier: Modifier = Modifier,
    color: Color = colorResource(R.color.divider),
    borderWidth: Dp = Dimensions.borderWidth.m
) {
    HorizontalDivider(
        modifier = modifier.fillMaxWidth(),
        color = color,
        thickness = borderWidth
    )
}