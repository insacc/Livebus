package com.insac.can.livebus.utils

import android.os.Looper

fun assertMainThread(methodName: String) {
    if (Looper.myLooper() == Looper.getMainLooper()) {
        throw LiveBusException("Cannot invoke " + methodName + " on a background"
                + " thread")
    }
}
