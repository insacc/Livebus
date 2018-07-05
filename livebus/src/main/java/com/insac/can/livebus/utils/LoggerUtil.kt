package com.insac.can.livebus.utils

import android.util.Log
import com.insac.can.livebus.BuildConfig

private const val LIVE_BUS_TAG = "liveBusDebug"
private const val LIVE_BUS_EXCEPTION_TAG = "liveBusException"
private const val LIVE_BUS_WARNING_TAG = "liveBusWarning"

fun logOnlyInDebug(logMessage: String) {
    if (BuildConfig.DEBUG) Log.d(LIVE_BUS_TAG, logMessage)
}

fun logExceptionOnlyInDebug(exception: Exception) {
    if (BuildConfig.DEBUG) Log.e(LIVE_BUS_EXCEPTION_TAG, Log.getStackTraceString(exception))
}

fun logWarningOnlyInDebug(warning: String) {
    if (BuildConfig.DEBUG) Log.w(LIVE_BUS_WARNING_TAG, warning)
}