package com.insac.can.livebus.core

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer

/**
 * Base class for all `LiveEvent` classes. Removes the observer
 * before adding it to prevent duplicate observers.
 */
open class LiveEventBase<T> : MutableLiveData<T>() {
    protected var mPendingObserve: Boolean = false

    override fun observe(owner: LifecycleOwner, observer: Observer<T>) {
        // Let the same observer to register once
        mPendingObserve = true
        removeObserver(observer)
        mPendingObserve = false
        super.observe(owner, observer)
    }
}
