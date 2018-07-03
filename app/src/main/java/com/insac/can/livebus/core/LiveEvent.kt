package com.insac.can.livebus.core

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer

open class LiveEvent<T> : MutableLiveData<T>() {
    private var mPendingObserve: Boolean = false

    override fun observe(owner: LifecycleOwner, observer: Observer<T>) {
        // Let the same observer to register once
        mPendingObserve = true
        removeObserver(observer)
        mPendingObserve = false
        super.observe(owner, observer)
    }

    override fun removeObserver(observer: Observer<T>) {
        super.removeObserver(observer)
        // Remove from bus if there are no observer left
        if (!mPendingObserve && !hasObservers()) {
            LiveBus.removeEvent(this)
        }
    }
}
