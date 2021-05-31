package com.example.myapplication.sensors

import android.content.Context
import android.hardware.Sensor
import com.example.myapplication.sensors.listeners.GyroscopeSensorListener
import com.example.myapplication.sensors.listeners.LoggingSensorEventListener


class GyroscopeSensor(mContext: Context) : AbstractSensor(mContext) {

    override fun getListener(): LoggingSensorEventListener {
        return GyroscopeSensorListener()
    }

    override fun getThreadName(): String {
        return "GyroscopeListenerThread"
    }

    override fun getSensorType(): Int {
        return Sensor.TYPE_GYROSCOPE
    }
}
