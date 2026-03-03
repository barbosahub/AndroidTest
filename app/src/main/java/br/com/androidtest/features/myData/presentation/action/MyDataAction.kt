package br.com.androidtest.features.myData.presentation.action

import br.com.androidtest.features.principal.event.PrincipalEvent

sealed interface MyDataAction {
    data object OnBackPressed : MyDataAction
    data object OnMyPlanClick : MyDataAction
    data object OnDownloadClick : MyDataAction
    data class OnPrivacyPolicyClick(val url: String? = null) : MyDataAction
    data object OnLogoutClick : MyDataAction
    data object LogoutAndCloseApp : MyDataAction

}