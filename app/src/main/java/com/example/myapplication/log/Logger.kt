package com.example.myapplication.log

import android.os.Environment
import com.example.myapplication.config.Configuration.Companion.LOG_DIR_NAME
import java.io.BufferedWriter
import java.io.File
import java.io.FileWriter
import java.io.IOException

class Logger(private var code: String, private var fileName: String, private var timestamp: String) {

    private val directory =
        Environment.getExternalStorageDirectory().absolutePath + "/$LOG_DIR_NAME"

    fun writeLog(text: String) {
        val log = File(directory)
        var logDirectoryExists = log.exists()
        if (!logDirectoryExists) {
            try {
                logDirectoryExists = log.mkdirs()
            } catch (e: Exception) {
                println(e.message)
            }
        }

        if (logDirectoryExists) {
            writeToFile(text)
        }
    }

    fun updateTimestamp(timestamp: String, code: String) {
        this.timestamp = timestamp
        this.code = code
    }

    private fun writeToFile(text: String) {
        val logFile = File("$directory/${code}_${fileName}_$timestamp.txt")

        if (!logFile.exists()) {
            logFile.createNewFile()
        }
        try {
            val buf = BufferedWriter(FileWriter(logFile, true))
            buf.append(text)
            buf.newLine()
            buf.close()
        } catch (e: IOException) {
            print(e.message)
        }
    }
}