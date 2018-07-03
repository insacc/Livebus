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

        private fun <T> addEvent(liveEventBase: LiveEventBase<T>) {
            // TODO implement add event method
        }

        fun <T> removeEvent(liveEventBase: LiveEventBase<T>) {
            // TODO implement remove event method
        }

        fun <T> postLiveEvent(tag: String, eventValue: T) {
            if (!mEvents.contains(tag)) {
                val liveEvent = LiveEvent<T>()
                mEvents[tag] = liveEvent
            }

            mEvents[tag]?.value = eventValue
        }

        fun <T> postSingleEvent(tag: String, eventValue: T) {

        }

        fun <T> postStickySingleEvent(tag: String, eventValue: T) {

        }

        fun <T> postStickyEven(tag: String, eventValue: T) {

        }

        fun removeStickyEvent(tag: String) {
            // TODO implement remove sticky event
        }
    }
}