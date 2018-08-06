package com.insac.can.livebus.core

import android.arch.lifecycle.Observer

/**
 * `LiveEvent` class which is a special `LiveEventBase` that gets
 * removed when there is no observer left but it'll be delivered
 * to at least one observer before it gets removed.
 */
class LiveEvent<T> : LiveEventBase<T>() {
    override fun removeObserver(observer: Observer<T>) {
        super.removeObserver(observer)
        // Remove from bus if there are no observer left
        if (!mPendingObserve && !hasObservers()) {
            LiveBus.getInstance().removeEvent(this)
        }
    }
}
