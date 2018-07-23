package com.insac.can.livebus.core

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.Observer
import android.support.annotation.MainThread
import android.support.annotation.Nullable
import com.insac.can.livebus.utils.logWarningOnlyInDebug
import java.util.concurrent.atomic.AtomicBoolean

internal class StickySingleLiveEvent<T> : LiveEventBase<T>() {
    private val mPending = AtomicBoolean(false)

    @MainThread
    override fun observe(owner: LifecycleOwner, observer: Observer<T>) {
        if (hasActiveObservers()) {
            logWarningOnlyInDebug("Multiple observers registered but only one will be notified of changes.")
        }

        super.observe(owner, Observer {
            if (mPending.compareAndSet(true, false)) {
                observer.onChanged(it)
            }
        })
    }

    @MainThread
    override fun setValue(@Nullable t: T?) {
        mPending.set(true)
        super.setValue(t)
    }

    /**
     * Used for cases where T is Void, to make calls cleaner.
     */
    @MainThread
    fun call() {
        value = null
    }

    fun isConsumed() : Boolean = !mPending.get()
}