package br.com.androidtest.features.myPlan.presentation.state

import br.com.androidtest.features.myData.domain.model.MyData
import br.com.androidtest.features.myPlan.domain.model.MyPlan

data class MyPlanUiState(
    val isLoading: Boolean = false,
    val result: MyPlan? = null
)