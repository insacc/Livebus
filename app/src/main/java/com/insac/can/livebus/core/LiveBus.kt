package com.insac.can.livebus.core

class LiveBus {
    companion object {
        private var mInstance: LiveBus? = null
        private var mEvents: HashMap<String, LiveEvent<Any>> = HashMap()

        fun getInstance(): LiveBus {
            if (mInstance == null) {
                mInstance = LiveBus()
            }

            return mInstance!!
        }

        private fun <T> addEvent(liveEvent: LiveEvent<T>) {
            // TODO implement add event method
        }

        fun <T> removeEvent(liveEvent: LiveEvent<T>) {
            // TODO implement remove event method
        }

        fun <T> broadcastEvent(tag: String, eventValue: T) {
            // TODO implement publish  event method
        }
    }
}