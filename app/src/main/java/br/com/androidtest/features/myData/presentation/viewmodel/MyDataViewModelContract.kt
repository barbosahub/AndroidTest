package br.com.androidtest.features.myData.presentation.viewmodel

import br.com.androidtest.features.myData.presentation.action.MyDataAction
import br.com.androidtest.features.myData.presentation.event.MyDataEvent
import br.com.androidtest.features.myData.presentation.state.MyDataUiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface MyDataViewModelContract {
    val state: StateFlow<MyDataUiState>
    val event: Flow<MyDataEvent>
    fun onAction(action: MyDataAction)
}