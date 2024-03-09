package com.veko.data

import java.io.ByteArrayOutputStream

class FileReader {

    fun readFile(fileName: String): String {
        javaClass.classLoader?.getResourceAsStream(fileName)?.let {
            val result = ByteArrayOutputStream()
            val buffer = ByteArray(1024)
            var length = it.read(buffer)
            while (length != -1) {
                result.write(buffer, 0, length)
                length = it.read(buffer)
            }
            return result.toString("UTF-8")
        }
        throw RuntimeException("Couldn't read file")
    }
}