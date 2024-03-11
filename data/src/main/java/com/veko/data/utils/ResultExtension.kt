package com.veko.data.utils

import retrofit2.Response

fun <T> Response<T>.bodyOrFailure(): Result<T> {
    body()?.let {
        return if (isSuccessful) {
            Result.Success(it)
        } else {
            Result.Failure(code())
        }
    }
    return Result.Failure(code())
}