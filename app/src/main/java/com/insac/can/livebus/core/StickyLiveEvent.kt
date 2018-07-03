package com.insac.can.livebus.core

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.Observer

class StickyLiveEvent<T> : LiveEventBase<T>() {
    private var mConsumeCount = 0

    override fun observe(owner: LifecycleOwner, observer: Observer<T>) {
        super.observe(owner, observer)
        mConsumeCount++
    }

    fun getConsumeCount(): Int = mConsumeCount

    override fun setValue(value: T) {
        super.setValue(value)
        mConsumeCount = 0
    }
}