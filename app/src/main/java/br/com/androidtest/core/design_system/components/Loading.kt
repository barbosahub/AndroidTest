package br.com.androidtest.core.design_system.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import br.com.androidtest.R
import br.com.androidtest.core.design_system.theme.AppTheme
import br.com.androidtest.core.design_system.theme.Dimensions
import br.com.androidtest.core.design_system.theme.Dimensions.borderWidth

@Preview
@Composable
fun LoadingPreview() {
    AppTheme {
        Box(modifier = Modifier.background(colorResource(R.color.bgPrimary))) {
            Loading()
        }
    }
}

@Composable
fun Loading(
    modifier: Modifier = Modifier,
    color: Color = colorResource(R.color.bgBrandSolid)
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            color = color,
            strokeWidth = 4.dp
        )
    }
}