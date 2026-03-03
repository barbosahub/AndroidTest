package br.com.androidtest.features.myData.presentation.action

sealed interface MyDataAction {
    data object OnBackPressed : MyDataAction
}