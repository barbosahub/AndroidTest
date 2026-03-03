package br.com.androidtest.features.myPlan.presentation.action

sealed interface MyPlanAction {
    data object OnBackPressed : MyPlanAction
    data object OnMessageClick : MyPlanAction

}