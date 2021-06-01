package com.example.myapplication.sensors.listeners

import android.hardware.SensorEventListener

//TODO consider changing it to abstract class
interface LoggingSensorEventListener : SensorEventListener {
    fun updateFilename(code: String)
}