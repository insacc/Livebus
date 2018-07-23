package com.insac.can.livebus.core

import com.insac.can.livebus.utils.exceptionWrapper

class LiveBus {
    companion object {
        private var mInstance: LiveBus? = null
        private var mEvents: HashMap<String, LiveEventBase<out Any?>> = HashMap()
        private const val CAST_EXCEPTION_MESSAGE = "LiveEvent casting exception! LiveEventBase saved on the " +
                "bus doesn't have the LiveEvent type"

        fun getInstance(): LiveBus {
            if (mInstance == null) {
                mInstance = LiveBus()
            }

            return mInstance!!
        }
    }

    fun <T> removeEvent(liveEvent: LiveEventBase<T>): Boolean {
        if (!mEvents.containsValue(liveEvent)) return false

        for (entry in mEvents.entries) {
            if (entry.value != liveEvent) continue

            mEvents.remove(entry.key)
            return true
        }

        return false
    }

    /**
     * This function creates a LiveEvent object and adds it to the
     * mEvents hashMap if necessary, otherwise it just updates the event's value
     *
     * @param tag The tag for the event
     * @param eventValue the value to be set to the event
     */
    fun <T> postLiveEvent(tag: String, eventValue: T) {
        if (!mEvents.contains(tag)) {
            val liveEvent = LiveEvent<T>()
            mEvents[tag] = liveEvent
        }

        exceptionWrapper(fun() {
            mEvents[tag]?.value = eventValue
        }, CAST_EXCEPTION_MESSAGE)
    }

    fun <T> postSingleEvent(tag: String, eventValue: T) {
        if (!mEvents.contains(tag)) {
            val liveEvent = SingleLiveEvent<T>()
            mEvents[tag] = liveEvent
        }

        exceptionWrapper(fun() {
            mEvents[tag]?.value = eventValue
        }, CAST_EXCEPTION_MESSAGE)
    }

    private fun <T> postStickySingleEvent(tag: String, eventValue: T) {
        if (!mEvents.contains(tag)) {
            val liveEvent = StickySingleLiveEvent<T>()
            mEvents[tag] = liveEvent
        }

        exceptionWrapper(fun() {
            mEvents[tag]?.value = eventValue
        }, CAST_EXCEPTION_MESSAGE)
    }

    fun <T> postStickyEvent(tag: String, eventValue: T) {
        if (!mEvents.contains(tag)) {
            val liveEvent = StickyLiveEvent<T>()
            mEvents[tag] = liveEvent
        }

        exceptionWrapper(fun() {
            mEvents[tag]?.value = eventValue
        }, CAST_EXCEPTION_MESSAGE)
    }

    fun removeEvent(tag: String) {
        if (!mEvents.contains(tag)) return

        mEvents.remove(tag)
    }

    fun subscribeEvent(tag: String): LiveEventBase<out Any?>? {
        if (mEvents.containsKey(tag)) {
            return mEvents[tag]
        }

        return null
    }

    fun <T> subscribeLiveEvent(tag: String, type: Class<T>): LiveEventBase<T> {
        return if (mEvents.containsKey(tag)) {
            exceptionWrapper(fun(): LiveEventBase<T> {
                return mEvents[tag] as LiveEvent<T>
            }, CAST_EXCEPTION_MESSAGE)
        } else {
            val liveEvent = LiveEvent<T>()
            mEvents[tag] = liveEvent
            liveEvent
        }
    }

    fun <T> subscribeSingleLiveEvent(tag: String, type: Class<T>): LiveEventBase<T> {
        return if (mEvents.containsKey(tag)) {
            exceptionWrapper(fun(): LiveEventBase<T> {
                return mEvents[tag] as SingleLiveEvent<T>
            }, CAST_EXCEPTION_MESSAGE)
        } else {
            val liveEvent = SingleLiveEvent<T>()
            mEvents[tag] = liveEvent
            liveEvent
        }
    }

    fun <T> subscribeStickyLiveEvent(tag: String, type: Class<T>): LiveEventBase<T> {
        return if (mEvents.containsKey(tag)) {
            exceptionWrapper(fun(): LiveEventBase<T> {
                return mEvents[tag] as StickyLiveEvent<T>
            }, CAST_EXCEPTION_MESSAGE)
        } else {
            val liveEvent = StickyLiveEvent<T>()
            mEvents[tag] = liveEvent
            liveEvent
        }
    }

    private fun <T> subscribeStickySingleLiveEvent(tag: String, type: Class<T>): LiveEventBase<T> {
        return if (mEvents.containsKey(tag)) {
            exceptionWrapper(fun(): LiveEventBase<T> {
                return mEvents[tag] as StickySingleLiveEvent<T>
            }, CAST_EXCEPTION_MESSAGE)
        } else {
            val liveEvent = StickySingleLiveEvent<T>()
            mEvents[tag] = liveEvent
            liveEvent
        }
    }
}