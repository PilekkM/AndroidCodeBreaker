package com.example.myapplication.sensors.listeners

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener

class GravitySensorListener : SensorEventListener {
    override fun onSensorChanged(event: SensorEvent?) {
        Thread.sleep(1000)
        println("[x] = " + event?.values!![0] + ", [y] = " + event?.values!![1] + ", [z] = " + event?.values!![2])
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        println(accuracy);
    }
}