package br.com.androidtest.features.myPlan.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.androidtest.core.util.onError
import br.com.androidtest.core.util.onSuccess
import br.com.androidtest.features.myData.domain.repository.IMyDataRepository
import br.com.androidtest.features.myPlan.domain.repository.IMyPlanRepository
import br.com.androidtest.features.myPlan.presentation.action.MyPlanAction
import br.com.androidtest.features.myPlan.presentation.event.MyPlanEvent
import br.com.androidtest.features.myPlan.presentation.state.MyPlanUiState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


class RWMyPlanViewModel(
    private val repository: IMyPlanRepository
) : ViewModel(), MyPlanViewModelContract {

    private val _state = MutableStateFlow(MyPlanUiState())
    override val state = _state.asStateFlow()

    private val _event = Channel<MyPlanEvent>()
    override val event = _event.receiveAsFlow()

    init {
        fetchMyPlan()
    }

    override fun onAction(action: MyPlanAction) {
        when (action) {
            is MyPlanAction.OnBackPressed -> {
                viewModelScope.launch {
                    _event.send(MyPlanEvent.OnBackPressed)
                }
            }

            MyPlanAction.OnMessageClick -> {}
        }
    }

    private fun fetchMyPlan() = viewModelScope.launch {
        _state.update {
            it.copy(
                isLoading = true,
            )
        }

        delay(2000)//Mock 2s

        repository.fetchMyPlanRW()
            .onSuccess { myPlan ->
                _state.update {
                    it.copy(
                        result = myPlan,
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