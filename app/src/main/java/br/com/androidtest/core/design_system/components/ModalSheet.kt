package br.com.androidtest.core.design_system.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.Dialog
import br.com.androidtest.R
import br.com.androidtest.core.design_system.theme.AppTheme


@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun ModalCardPreview() {
    AppTheme {
        var showDialog by remember { mutableStateOf(true) }
        ModalCard(
            showDialog = showDialog,
            onDismiss = { showDialog = false },
            content = {
                Box(
                    modifier = Modifier.background(colorResource(R.color.bgPrimary))
                ) {
                    Text("Im a modal")
                }
            }
        )
    }
}

@Composable
fun ModalCard(
    showDialog: Boolean,
    onDismiss: () -> Unit,
    content: @Composable () -> Unit
) {
    if (showDialog) {
        Dialog(onDismissRequest = onDismiss) {
            content()
        }
    }
}