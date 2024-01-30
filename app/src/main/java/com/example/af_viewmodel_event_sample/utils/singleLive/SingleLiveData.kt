package com.example.af_viewmodel_event_sample.utils.singleLive

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import com.example.af_viewmodel_event_sample.utils.Event

abstract class SingleLiveData<T> {

    // Wrap Event
    private val liveData = MutableLiveData<Event<T>>()

    protected constructor()

    protected constructor(value: T) {
        liveData.value = Event(value)
    }

    protected open fun setValue(value: T) {
        liveData.value = Event(value)
    }

    protected open fun postValue(value: T) {
        liveData.postValue(Event(value))
    }

    // get data
    fun getValue() = liveData.value?.peekContent()

    // get first data
    fun getNotHandledValue() = liveData.value?.getContentIfNotHandled()

    // observe data
    fun observePeek(owner: LifecycleOwner, onResult: (T) -> Unit) {
        liveData.observe(owner) { onResult(it.peekContent()) }
    }

    // observe first data
    fun observe(owner: LifecycleOwner, onResult: (T) -> Unit) {
        liveData.observe(owner) { it.getContentIfNotHandled()?.let(onResult) }
    }
}