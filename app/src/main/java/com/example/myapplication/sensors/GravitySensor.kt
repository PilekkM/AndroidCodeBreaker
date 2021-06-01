package com.example.myapplication.sensors

import android.content.Context
import android.hardware.Sensor
import com.example.myapplication.sensors.listeners.GravitySensorListener
import com.example.myapplication.sensors.listeners.LoggingSensorEventListener

class GravitySensor(mContext: Context) : AbstractSensor(mContext) {

    override fun getListener(): LoggingSensorEventListener? {
        return GravitySensorListener()
    }

    override fun getThreadName(): String? {
        return "GravitySensorThread"
    }

    override fun getSensorType(): Int {
        return Sensor.TYPE_GRAVITY
    }

}