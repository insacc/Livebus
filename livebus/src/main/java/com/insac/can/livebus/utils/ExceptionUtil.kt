package com.insac.can.livebus.utils


fun exceptionWrapper(func: () -> Unit) {
    try {
        func()
    } catch (ex: Exception) {
        logExceptionOnlyInDebug(ex)
    }
}

fun <T> exceptionWrapper(func: () -> T, defaultValue: T): T {
    return try {
        func()
    } catch (ex: Exception) {
        logExceptionOnlyInDebug(ex)
        defaultValue
    }
}

fun <T> exceptionWrapper(func: () -> T): T {
    return try {
        func()
    } catch (ex: Exception) {
        logExceptionOnlyInDebug(ex)
        throw LiveBusException(ex.toString())
    }
}

fun <T> exceptionWrapper(exceptionMessage: String, func: () -> T): T {
    return try {
        func()
    } catch (ex: Exception) {
        logExceptionOnlyInDebug(ex)
        throw LiveBusException(exceptionMessage)
    }
}

fun exceptionWrapper(exceptionMessage: String, func: () -> Unit) {
    try {
        func()
    } catch (ex: Exception) {
        logExceptionOnlyInDebug(ex)
        throw LiveBusException(exceptionMessage)
    }
}
