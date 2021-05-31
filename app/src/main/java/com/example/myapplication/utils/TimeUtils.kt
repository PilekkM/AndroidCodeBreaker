package com.example.myapplication.utils

import java.text.SimpleDateFormat
import java.util.*

class TimeUtils {

    companion object {

        val ONLY_FULL_TIME_PATTERN = "HH:mm:ss.SSS"
        val ONLY_FULL_TIME_WITHOUT_COLONS_PATTERN = "HH-mm-ss"

        fun getActualTimestamp(pattern: String): String {
            val formatter = SimpleDateFormat(pattern, Locale.ENGLISH)
            val time = formatter.format(Date())
            return "[$time] "
        }
    }
}