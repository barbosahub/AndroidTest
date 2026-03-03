package br.com.androidtest.features.myData.presentation.event

import br.com.androidtest.features.myData.presentation.action.MyDataAction

sealed interface MyDataEvent {
    data object OnBackPressed : MyDataEvent
    data object NavigateToMyPlan : MyDataEvent
    data object Download : MyDataEvent
    data class NavigateToPrivacyPolicy(val url: String? = null) : MyDataEvent
    data object ShowLogout : MyDataEvent
    data object LogoutAndCloseApp : MyDataEvent
}