package com.example.af_viewmodel_event_sample.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class StateFlowViewModel : ViewModel() {
    private val _someData = MutableStateFlow<Int?>(null)
    val someData = _someData.asStateFlow()

    fun setData() {
        viewModelScope.launch {
            _someData.value?.let { _someData.emit(it + 1) } ?: _someData.emit(0)
        }
    }
}