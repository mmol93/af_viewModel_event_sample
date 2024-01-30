package com.example.af_viewmodel_event_sample.viewModels

import androidx.lifecycle.ViewModel
import com.example.af_viewmodel_event_sample.utils.singleLive.MutableSingleLiveData
import com.example.af_viewmodel_event_sample.utils.singleLive.SingleLiveData

class SingleViewModel : ViewModel() {
    private val _someData = MutableSingleLiveData<Int>()
    val someData: SingleLiveData<Int> = _someData

    fun setData() {
        if (_someData.getNotHandledValue() == null) {
            _someData.setValue(0)
        } else {
            _someData.setValue(_someData.getNotHandledValue()!! + 1)
        }
    }
}