package com.insac.can.livebus.utils

import java.util.concurrent.Callable

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

fun <T> exceptionWrapper(func: () -> T, exceptionMessage: String): T {
    return try {
        func()
    } catch (ex: Exception) {
        logExceptionOnlyInDebug(ex)
        throw LiveBusException(exceptionMessage)
    }
}

fun exceptionWrapper(func: () -> Unit, exceptionMessage: String) {
    try {
        func()
    } catch (ex: Exception) {
        logExceptionOnlyInDebug(ex)
        throw LiveBusException(exceptionMessage)
    }
}
