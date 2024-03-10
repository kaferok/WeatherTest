package com.veko.data

import java.io.ByteArrayOutputStream

object FileReader {

    fun readFile(fileName: String): String {
        return this::class.java.classLoader?.getResource(fileName)?.readText().orEmpty()
    }
}