package org.example.cmp_passwowapp.core.presentation.util

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberUpdatedState
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.flow.Flow

@Suppress("SuspiciousIndentation")
@Composable
fun<T> ObserveAsEvents(flow: Flow<T>, onEvent:(T)-> Unit){
    val lifecycleOwner = LocalLifecycleOwner.current
    LaunchedEffect(flow, lifecycleOwner.lifecycle){
        lifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED){
            flow.collect(onEvent)
        }
    }
}
