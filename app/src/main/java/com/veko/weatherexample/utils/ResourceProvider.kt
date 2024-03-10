package com.veko.weatherexample.utils

import android.content.Context
import androidx.annotation.ArrayRes
import androidx.annotation.StringRes

class ResourceProvider(context: Context) {
    private val resources = context.resources

    fun getString(@StringRes id: Int, parameter: String? = null): String =
        resources.getString(id, parameter)

}