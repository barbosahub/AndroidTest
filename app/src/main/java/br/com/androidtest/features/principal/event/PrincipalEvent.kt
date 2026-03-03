package br.com.androidtest.features.principal.event

sealed interface PrincipalEvent {
    data object OnBackPressed : PrincipalEvent
    data object NavigateToNewPlatformMyData : PrincipalEvent
    data object NavigateToOldPlatformMyData : PrincipalEvent
    data object LogoutAndCloseApp : PrincipalEvent
}