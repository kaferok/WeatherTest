package com.veko.data.utils

fun <T> Result<T>.doOnSuccess(onSuccess: (T) -> Unit): Result<T> {
    if (isSuccess) {
        getOrNull()?.run(onSuccess)
    }

    return this
}

fun <T> Result<T>.doOnError(onError: (Throwable) -> Unit): Result<T> {
    if (isFailure) {
        exceptionOrNull()?.run(onError)
    }

    return this
}

fun <T> Result<T>.doOnFinish(onFinish: () -> Unit): Result<T> {
    onFinish()
    return this
}

suspend fun <T> safeRequest(block: suspend () -> Result<T>): Result<T> = try {
    block()
} catch (e: Exception) {
    Result.failure(e)
}