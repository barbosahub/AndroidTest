package br.com.androidtest.features.myData.presentation.state

import br.com.androidtest.features.myData.domain.model.MyData

data class MyDataUiState(
    val isLoading: Boolean = false,
    val result: MyData? = null
)