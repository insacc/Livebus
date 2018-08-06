package com.insac.can.livebus.core

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.Observer

/**
 * `StickyLiveEvent` class which is a special `LiveEventBase` that gets
 * removed only if `removeEvent(..)` gets explicitly called.
 */
class StickyLiveEvent<T> : LiveEventBase<T>() {
    private var mConsumeCount = 0

    override fun observe(owner: LifecycleOwner, observer: Observer<T>) {
        super.observe(owner, observer)
        mConsumeCount++
    }

    private fun getConsumeCount(): Int = mConsumeCount

    override fun setValue(value: T) {
        super.setValue(value)
        mConsumeCount = 0
    }
}