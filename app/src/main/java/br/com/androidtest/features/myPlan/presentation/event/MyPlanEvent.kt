package br.com.androidtest.features.myPlan.presentation.event

import br.com.androidtest.features.myData.presentation.action.MyDataAction

sealed interface MyPlanEvent {
    data object OnBackPressed : MyPlanEvent
}