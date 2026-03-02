package br.com.androidtest.core

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import br.com.androidtest.core.design_system.theme.AppTheme
import br.com.androidtest.features.principal.navigation.RootNavHost

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            AppTheme {
                RootNavHost()
            }
        }
    }
}