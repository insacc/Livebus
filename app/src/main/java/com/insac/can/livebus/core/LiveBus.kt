package com.insac.can.livebus.core

class LiveBus {
    companion object {
        var mInstance : LiveBus? = null
            private set

        fun getInstance(): LiveBus {
            if (mInstance == null) {
                mInstance = LiveBus()
            }

            return mInstance!!
        }
    }
}