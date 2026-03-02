package br.com.androidtest.features.myData.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import br.com.androidtest.core.design_system.theme.AppTheme

@Preview
@Composable
fun MyDataScreenPreview() {
    AppTheme {
        MyDataScreen()
    }
}


@Composable
fun MyDataScreenRoot() {
    MyDataScreen()
}

@Composable
fun MyDataScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text("teste")
    }
}