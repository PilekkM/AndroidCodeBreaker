package com.example.myapplication.sensors

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEventListener
import com.example.myapplication.sensors.listeners.GravitySensorListener

class GravitySensor(mContext: Context) : AbstractSensor(mContext) {

    override fun getListener(): SensorEventListener? {
        return GravitySensorListener()
    }

    override fun getThreadName(): String? {
        return "GravitySensorThread"
    }

    override fun getSensorType(): Int {
        return Sensor.TYPE_GRAVITY
    }

}