package br.com.androidtest.features.myData.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.androidtest.core.util.onError
import br.com.androidtest.core.util.onSuccess
import br.com.androidtest.features.myData.domain.repository.IMyDataRepository
import br.com.androidtest.features.myData.presentation.action.MyDataAction
import br.com.androidtest.features.myData.presentation.event.MyDataEvent
import br.com.androidtest.features.myData.presentation.state.MyDataUiState
import br.com.androidtest.features.principal.action.PrincipalAction
import br.com.androidtest.features.principal.event.PrincipalEvent
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


class NPMyDataViewModel(
    private val repository: IMyDataRepository
) : ViewModel(), MyDataViewModelContract {

    private val _state = MutableStateFlow(MyDataUiState())
    override val state = _state.asStateFlow()

    private val _event = Channel<MyDataEvent>()
    override val event = _event.receiveAsFlow()

    init {
        fetchMyData()
    }

    override fun onAction(action: MyDataAction) {
        when (action) {
            is MyDataAction.OnBackPressed -> {
                viewModelScope.launch {
                    _event.send(MyDataEvent.OnBackPressed)
                }
            }

            MyDataAction.OnDownloadClick -> {
                viewModelScope.launch {
                    _event.send(MyDataEvent.Download)
                }
            }

            MyDataAction.OnLogoutClick -> {
                viewModelScope.launch {
                    _event.send(MyDataEvent.ShowLogout)
                }
            }

            MyDataAction.OnMyPlanClick -> {
                viewModelScope.launch {
                    _event.send(MyDataEvent.NavigateToMyPlan)
                }
            }

            is MyDataAction.OnPrivacyPolicyClick -> {
                viewModelScope.launch {
                    _event.send(MyDataEvent.NavigateToPrivacyPolicy(action.url))
                }
            }

            MyDataAction.LogoutAndCloseApp -> {
                viewModelScope.launch {
                    _event.send(MyDataEvent.LogoutAndCloseApp)
                }
            }
        }
    }


    private fun fetchMyData() = viewModelScope.launch {
        _state.update {
            it.copy(
                isLoading = true,
            )
        }

        delay(2000)//Mock 2s

        repository.fetchMyDataNP()
            .onSuccess { myData ->
                _state.update {
                    it.copy(
                        result = myData,
                        isLoading = false,
                    )
                }
            }
            .onError { error ->
                _state.update {
                    it.copy(
                        isLoading = false
                    )
                }
            }

    }
}