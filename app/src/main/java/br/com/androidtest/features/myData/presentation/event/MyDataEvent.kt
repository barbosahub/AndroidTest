package br.com.androidtest.features.myData.presentation.event

sealed interface MyDataEvent {
    data object OnBackPressed : MyDataEvent
}