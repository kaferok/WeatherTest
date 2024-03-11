package com.veko.weatherexample.utils.ext

fun String.capitalizeAnyCase() = mapIndexed { index, char ->
    if (index == 0) {
        char.titlecaseChar()
    } else {
        char.lowercaseChar()
    }
}.joinToString("")