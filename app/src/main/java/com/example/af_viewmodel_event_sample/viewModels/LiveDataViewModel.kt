package com.example.af_viewmodel_event_sample.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LiveDataViewModel: ViewModel() {
    private val _someData = MutableLiveData<Int>()
    val someData: LiveData<Int> = _someData

    fun setData() {
        if (_someData.value == null) {
            _someData.value = 0
        } else {
            _someData.value = _someData.value!! + 1
        }
    }
}