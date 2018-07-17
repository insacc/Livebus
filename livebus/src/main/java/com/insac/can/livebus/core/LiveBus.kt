package com.insac.can.livebus.core

import com.insac.can.livebus.utils.LiveBusException

class LiveBus {
    companion object {
        private var mInstance: LiveBus? = null
        private var mEvents: HashMap<String, LiveEventBase<out Any?>> = HashMap()

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

        mEvents[tag]?.value = eventValue
    }

    fun <T> postSingleEvent(tag: String, eventValue: T) {
        if (!mEvents.contains(tag)) {
            val liveEvent = SingleLiveEvent<T>()
            mEvents[tag] = liveEvent
        }

        mEvents[tag]?.value = eventValue
    }

    private fun <T> postStickySingleEvent(tag: String, eventValue: T) {
        if (!mEvents.contains(tag)) {
            val liveEvent = StickySingleLiveEvent<T>()
            mEvents[tag] = liveEvent
        }

        mEvents[tag]?.value = eventValue
    }

    fun <T> postStickyEvent(tag: String, eventValue: T) {
        if (!mEvents.contains(tag)) {
            val liveEvent = StickyLiveEvent<T>()
            mEvents[tag] = liveEvent
        }

        mEvents[tag]?.value = eventValue
    }

    fun <T> removeStickyEvent(tag: String) {
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
            try {
                mEvents[tag] as LiveEvent<T>
            } catch (e: Exception) {
                throw LiveBusException("LiveEvent casting exception! LiveEventBase saved on the " +
                        "bus doesn't have the LiveEvent type")
            }
        } else {
            val liveEvent = LiveEvent<T>()
            mEvents.put(tag, liveEvent)
            liveEvent
        }
    }

    fun <T> subscribeSingleLiveEvent(tag: String, type: Class<T>): LiveEventBase<T> {
        return if (mEvents.containsKey(tag)) {
            try {
                mEvents[tag] as SingleLiveEvent<T>
            } catch (e: Exception) {
                throw LiveBusException("LiveEvent casting exception! LiveEventBase saved on the " +
                        "bus doesn't have the SingleLiveEvent type")
            }
        } else {
            val liveEvent = SingleLiveEvent<T>()
            mEvents[tag] = liveEvent
            liveEvent
        }
    }

    fun <T> subscribeStickyLiveEvent(tag: String, type: Class<T>): LiveEventBase<T> {
        return if (mEvents.containsKey(tag)) {
            try {
                mEvents[tag] as StickyLiveEvent<T>
            } catch (e: Exception) {
                throw LiveBusException("LiveEvent casting exception! LiveEventBase saved on the " +
                        "bus doesn't have the StickyLiveEvent type")
            }
        } else {
            val liveEvent = StickyLiveEvent<T>()
            mEvents[tag] = liveEvent
            liveEvent
        }
    }

    private fun <T> subscribeStickySingleLiveEvent(tag: String, type: Class<T>): LiveEventBase<T> {
        return if (mEvents.containsKey(tag)) {
            try {
                mEvents[tag] as StickySingleLiveEvent<T>
            } catch (e: Exception) {
                throw LiveBusException("LiveEvent casting exception! LiveEventBase saved on the " +
                        "bus doesn't have the StickySingleLiveEvent type")
            }
        } else {
            val liveEvent = StickySingleLiveEvent<T>()
            mEvents[tag] = liveEvent
            liveEvent
        }
    }
}