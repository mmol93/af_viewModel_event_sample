package com.example.af_viewmodel_event_sample.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class StateFlowEventViewModel : ViewModel() {
    private val _eventFlow = MutableSharedFlow<SharedFlowEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    fun setFirstData() {
        emitEvent(SharedFlowEvent.FirstData(1))
    }

    fun setSecondData() {
        emitEvent(SharedFlowEvent.SecondData("abc"))
    }

    fun setThirdData() {
        emitEvent(SharedFlowEvent.ThirdData(true))
    }

    fun setDelayedData1() {
        viewModelScope.launch {
            delay(5000L)
            _eventFlow.emit(SharedFlowEvent.DelayedData("Delayed"))
        }
    }

    private fun emitEvent(sharedFlowEvent: SharedFlowEvent) {
        viewModelScope.launch {
            _eventFlow.emit(sharedFlowEvent)
        }
    }

    sealed class SharedFlowEvent {
        data class FirstData(val number: Int) : SharedFlowEvent()
        data class SecondData(val text: String) : SharedFlowEvent()
        data class ThirdData(val ox: Boolean) : SharedFlowEvent()
        data class DelayedData(val text: String) : SharedFlowEvent()
    }
}