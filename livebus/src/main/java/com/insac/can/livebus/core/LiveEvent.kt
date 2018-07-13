package com.insac.can.livebus.core

import android.arch.lifecycle.Observer

class LiveEvent<T> : LiveEventBase<T>() {
    override fun removeObserver(observer: Observer<T>) {
        super.removeObserver(observer)
        // Remove from bus if there are no observer left
        if (!mPendingObserve && !hasObservers()) {
            LiveBus.getInstance().removeEvent(this)
        }
    }
}
