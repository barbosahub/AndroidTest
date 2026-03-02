package br.com.androidtest.features.principal.action

sealed interface PrincipalAction {
    data object OnBackPressed : PrincipalAction
    data object NewPlatformClick : PrincipalAction
    data object OldPlatformClick : PrincipalAction
    data object OnLogoutClick : PrincipalAction
}