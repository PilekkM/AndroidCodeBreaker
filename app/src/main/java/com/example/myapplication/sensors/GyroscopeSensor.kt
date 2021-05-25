package com.example.myapplication.sensors

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEventListener
import com.example.myapplication.sensors.listeners.GyroscopeSensorListener



class GyroscopeSensor(mContext: Context) : AbstractSensor(mContext) {

    override fun getListener(): SensorEventListener {
        return GyroscopeSensorListener()
    }

    override fun getThreadName(): String {
        return "GyroscopeListenerThread"
    }

    override fun getSensorType(): Int {
        return Sensor.TYPE_GYROSCOPE
    }
}
