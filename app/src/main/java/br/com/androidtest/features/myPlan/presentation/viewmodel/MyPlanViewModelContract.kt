package br.com.androidtest.features.myPlan.presentation.viewmodel

import br.com.androidtest.features.myPlan.presentation.action.MyPlanAction
import br.com.androidtest.features.myPlan.presentation.event.MyPlanEvent
import br.com.androidtest.features.myPlan.presentation.state.MyPlanUiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface MyPlanViewModelContract {
    val state: StateFlow<MyPlanUiState>
    val event: Flow<MyPlanEvent>
    fun onAction(action: MyPlanAction)
}