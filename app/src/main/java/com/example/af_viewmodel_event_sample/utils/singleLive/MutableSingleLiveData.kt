package com.example.af_viewmodel_event_sample.utils.singleLive

class MutableSingleLiveData<T> : SingleLiveData<T> {

    constructor() : super()

    constructor(value: T) : super(value)

    public override fun postValue(value: T) {
        super.postValue(value)
    }

    public override fun setValue(value: T) {
        super.setValue(value)
    }
}