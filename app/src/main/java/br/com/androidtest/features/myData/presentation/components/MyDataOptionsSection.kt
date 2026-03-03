package br.com.androidtest.features.myData.presentation.components

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
import androidx.compose.ui.tooling.preview.Preview
import br.com.androidtest.R
import br.com.androidtest.core.design_system.components.MenuItem
import br.com.androidtest.core.design_system.theme.AppTheme
import br.com.androidtest.core.design_system.theme.Dimensions
import br.com.androidtest.core.util.toUiIcon
import br.com.androidtest.features.myData.domain.model.Option


@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun MyDataOptionsSectionPreview() {
    AppTheme {
        Box(modifier = Modifier.padding(all = Dimensions.spacing.space16dp)) {
            MyDataOptionsSection(listOf(), Modifier)
        }
    }
}

@Composable
fun MyDataOptionsSection(
    options: List<Option>?,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        options?.forEach { option ->
            MenuItem(
                labelText = option.title ?: "Sem título",
                leadingIcon = (option.iconUrl ?: option.iconType).toUiIcon(),
                trailingIcon = Icons.Filled.ChevronRight
            ){

            }
        }
    }
}