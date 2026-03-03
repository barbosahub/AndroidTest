package br.com.androidtest.core.design_system.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import br.com.androidtest.R
import br.com.androidtest.core.design_system.theme.AppTheme
import br.com.androidtest.core.design_system.theme.Dimensions


@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
private fun LrTopAppBarNavigationPreview() {
    AppTheme {

        val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())

        Column(
            modifier = Modifier
                .background(colorResource(R.color.bgBrandSolid))
                .padding(all = 24.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            LrTopAppBarNavigation(
                leadingIconSlot = {
                    Icon(
                        imageVector = Icons.Filled.ChevronLeft,
                        contentDescription = stringResource(R.string.back),
                        tint = Color.White
                    )
                },
                scrollBehavior = scrollBehavior
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LrTopAppBarNavigation(
    modifier: Modifier = Modifier,
    customHorizontalPadding: Dp? = null,
    leadingIconSlot: @Composable (() -> Unit?) = {},
    titleSlot: @Composable () -> Unit? = {},
    bgColor: Color = colorResource(R.color.bgPrimary),
    bottomContentSlot: @Composable () -> Unit? = {},
    trailingActionsSlot: @Composable RowScope.() -> Unit = {},
    windowInsets: WindowInsets = TopAppBarDefaults.windowInsets,
    scrollBehavior: TopAppBarScrollBehavior? = null
) {

    Column(verticalArrangement = Arrangement.spacedBy(Dimensions.spacing.space8dp)) {
        TopAppBar(
            modifier = Modifier
                .background(bgColor)
                .fillMaxWidth()
                .padding(horizontal = customHorizontalPadding ?: Dimensions.spacing.spaceNone),
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color.Transparent,
                titleContentColor = colorResource(R.color.textPrimary)
            ),

            title = { titleSlot.invoke() },
            navigationIcon = {
                leadingIconSlot.invoke()
            },
            actions = {
                trailingActionsSlot()
            },
            scrollBehavior = scrollBehavior,
            windowInsets = windowInsets
        )
        bottomContentSlot()
    }
}
