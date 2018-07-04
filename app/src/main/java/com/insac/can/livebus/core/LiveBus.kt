package com.insac.can.livebus.core

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

    fun <T> postStickySingleEvent(tag: String, eventValue: T) {
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
}