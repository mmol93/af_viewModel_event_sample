package com.example.af_viewmodel_event_sample.utils

import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.MutableSharedFlow
import java.util.concurrent.atomic.AtomicBoolean

interface EventFlow<out T> : Flow<T> {
    companion object {
        const val DEFAULT_REPLAY: Int = 3
    }
}

interface MutableEventFlow<T> : EventFlow<T>, FlowCollector<T>

fun <T> MutableEventFlow(replay: Int = EventFlow.DEFAULT_REPLAY): MutableEventFlow<T> =
    EventFlowImpl(replay)

private class ReadOnlyEventFlow<T>(flow: EventFlow<T>) : EventFlow<T> by flow

fun <T> MutableEventFlow<T>.asEventFlow(): EventFlow<T> = ReadOnlyEventFlow(this)

// FlowCollectorを相続することでSharedFlowにデータをemitする前にデータをコントロールできる
private class EventFlowImpl<T>(replay: Int) : MutableEventFlow<T> {

    private val flow: MutableSharedFlow<EventFlowSlot<T>> = MutableSharedFlow(replay = replay)

    @InternalCoroutinesApi
    override suspend fun collect(collector: FlowCollector<T>) = flow.collect { slot ->
        // データが消費されてない場合のみデータをemit
        if (!slot.markConsumed()) {
            collector.emit(slot.value)
        }
    }

    override suspend fun emit(value: T) {
        flow.emit(EventFlowSlot(value))
    }
}

private class EventFlowSlot<T>(val value: T) {

    // for multi-thread emit safety
    private val consumed: AtomicBoolean = AtomicBoolean(false)

    // check consume history
    fun markConsumed(): Boolean = consumed.getAndSet(true)
}