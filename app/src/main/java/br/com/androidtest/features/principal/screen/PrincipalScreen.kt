package br.com.androidtest.features.principal.screen

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import br.com.androidtest.R
import br.com.androidtest.core.design_system.components.MenuItem
import br.com.androidtest.core.design_system.theme.AppTheme
import br.com.androidtest.features.principal.action.PrincipalAction


@Preview
@Composable
fun PrincipalScreenPreview() {
    AppTheme {
        PrincipalScreen(
            onAction = {}
        )
    }
}


@Composable
fun PrincipalScreenRoot(
    onAction: (PrincipalAction) -> Unit
) {
    BackHandler {
        onAction(PrincipalAction.OnBackPressed)
    }

    PrincipalScreen(onAction)
}

@Composable
fun PrincipalScreen(
    onAction: (PrincipalAction) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        MenuItem(
            labelText = stringResource(R.string.new_platform),
            trailingIcon = Icons.Filled.ChevronRight
        ) {
            onAction(PrincipalAction.NewPlatformClick)
        }

        MenuItem(
            labelText = stringResource(R.string.old_platform),
            trailingIcon = Icons.Filled.ChevronRight
        ) {
            onAction(PrincipalAction.OldPlatformClick)
        }
    }
}