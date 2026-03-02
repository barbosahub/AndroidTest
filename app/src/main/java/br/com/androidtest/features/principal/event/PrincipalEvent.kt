package br.com.androidtest.features.principal.event

sealed interface PrincipalEvent {
    data object OnBackPressed : PrincipalEvent
    data object NavigateToNewPlatform : PrincipalEvent
    data object NavigateToOldPlatform : PrincipalEvent
    data object LogoutAndCloseApp : PrincipalEvent
}