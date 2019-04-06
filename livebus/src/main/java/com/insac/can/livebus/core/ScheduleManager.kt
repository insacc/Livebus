package com.insac.can.livebus.core

import java.util.*

class ScheduleManager {
    companion object {
        private var mInstance: ScheduleManager? = null

        fun getInstance(): ScheduleManager = if (mInstance == null) {
            mInstance = ScheduleManager()
            mInstance!!
        } else {
            mInstance!!
        }
    }

    private var mTimer: Timer? = null

    init {
        mTimer = Timer()
    }

    fun schedule(delayInMilliseconds: Long, func: () -> Unit) {
        mInstance?.schedule(delayInMilliseconds) {
            func()
        }
    }
}
