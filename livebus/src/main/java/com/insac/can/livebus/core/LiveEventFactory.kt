package com.insac.can.livebus.core

fun <T> createLiveEvent(liveEventClass: Class<T>): LiveEventBase<T> {
    return when (liveEventClass) {
        LiveEvent::class.java -> {
            LiveEvent()
        }

        SingleLiveEvent::class.java -> {
            SingleLiveEvent()
        }

        StickyLiveEvent::class.java -> {
            StickyLiveEvent()
        }

        StickySingleLiveEvent::class.java -> {
            StickySingleLiveEvent()
        }

        else -> {
            LiveEvent()
        }
    }
}
