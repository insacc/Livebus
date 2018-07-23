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
