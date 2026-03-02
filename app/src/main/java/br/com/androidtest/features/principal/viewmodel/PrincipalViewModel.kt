package br.com.androidtest.features.principal.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.androidtest.features.principal.action.PrincipalAction
import br.com.androidtest.features.principal.event.PrincipalEvent
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class PrincipalViewModel : ViewModel() {
    private val _event = Channel<PrincipalEvent>()
    val event = _event.receiveAsFlow()

    fun onAction(action: PrincipalAction) {
        when (action) {
            is PrincipalAction.OnBackPressed -> {
                viewModelScope.launch {
                    _event.send(PrincipalEvent.OnBackPressed)
                }
            }

            PrincipalAction.NewPlatformClick -> {
                viewModelScope.launch {
                    _event.send(PrincipalEvent.NavigateToNewPlatform)
                }
            }

            PrincipalAction.OldPlatformClick -> {
                viewModelScope.launch {
                    _event.send(PrincipalEvent.NavigateToOldPlatform)
                }
            }

            PrincipalAction.OnLogoutClick -> {
                viewModelScope.launch {
                    _event.send(PrincipalEvent.LogoutAndCloseApp)
                }
            }
        }
    }
}