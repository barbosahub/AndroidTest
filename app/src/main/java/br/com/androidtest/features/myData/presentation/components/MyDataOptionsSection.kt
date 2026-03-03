package br.com.androidtest.features.myData.presentation.components

import android.R.attr.onClick
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import br.com.androidtest.R
import br.com.androidtest.core.design_system.components.MenuItem
import br.com.androidtest.core.design_system.theme.AppTheme
import br.com.androidtest.core.design_system.theme.Dimensions
import br.com.androidtest.core.util.toUiIcon
import br.com.androidtest.features.myData.domain.model.IconEnum
import br.com.androidtest.features.myData.domain.model.Option


@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun MyDataOptionsSectionPreview() {
    AppTheme {
        Box(modifier = Modifier.padding(all = Dimensions.spacing.space16dp)) {
            MyDataOptionsSection(listOf(), Modifier) { action, url ->

            }
        }
    }
}

@Composable
fun MyDataOptionsSection(
    options: List<Option>?,
    modifier: Modifier = Modifier,
    onClick: (action: String?, url: String?) -> Unit
) {
    Column(
        modifier = modifier
    ) {
        if (options != null) {
            LoadNewPlatformItems(options, onClick)
        } else {
            LoadOldPlatformItems(onClick)
        }
    }
}

@Composable
private fun LoadOldPlatformItems(onClick: (action: String?, url: String?) -> Unit) {
    Column {
        MenuItem(
            labelText = stringResource(R.string.my_plan),
            leadingIcon = IconEnum.DOCUMENT.key.toUiIcon(),
            trailingIcon = Icons.Filled.ChevronRight
        ) {
            onClick((IconEnum.DOCUMENT.key), null)
        }

        MenuItem(
            labelText = stringResource(R.string.download),
            leadingIcon = IconEnum.DOWNLOAD.key.toUiIcon(),
            trailingIcon = Icons.Filled.ChevronRight
        ) {
            onClick((IconEnum.DOWNLOAD.key), null)
        }

        MenuItem(
            labelText = stringResource(R.string.logout),
            leadingIcon = IconEnum.BLOCK.key.toUiIcon(),
            trailingIcon = Icons.Filled.ChevronRight
        ) {
            onClick((IconEnum.BLOCK.key), null)
        }
    }

}

@Composable
private fun LoadNewPlatformItems(
    options: List<Option>?,
    onClick: (action: String?, url: String?) -> Unit
) {
    options?.forEach { option ->
        MenuItem(
            labelText = option.title,
            leadingIcon = (option.iconUrl ?: option.iconType).toUiIcon(),
            trailingIcon = Icons.Filled.ChevronRight
        ) {
            onClick((option.iconUrl ?: option.iconType), option.url)
        }
    }
}