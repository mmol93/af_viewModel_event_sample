package com.example.af_viewmodel_event_sample.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.af_viewmodel_event_sample.utils.Event

class EventLiveDataViewModel : ViewModel() {
    private val _someData = MutableLiveData<Event<Int>>()
    val someData: LiveData<Event<Int>> = _someData

    fun setData() {
        if (_someData.value?.getContentIfNotHandled() == null) {
            _someData.value = Event(0)
        } else {
            _someData.value = Event(_someData.value?.getContentIfNotHandled() as Int + 1)
        }
    }
}