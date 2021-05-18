package com.example.myapplication.sensors.listeners

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import com.example.myapplication.config.Configuration.Companion.GRAVITY_SENSOR_LOG_FILENAME
import com.example.myapplication.config.Configuration.Companion.SENSOR_ACCURACY_MILLIS
import com.example.myapplication.log.Logger
import com.example.myapplication.utils.TimeUtils
import com.example.myapplication.utils.TimeUtils.Companion.getActualTimestamp

class GravitySensorListener : SensorEventListener {

    private val logger = Logger(GRAVITY_SENSOR_LOG_FILENAME)

    override fun onSensorChanged(event: SensorEvent?) {
        val logMessage = getActualTimestamp(TimeUtils.ONLY_FULL_TIME_PATTERN) +
                "[x] = " + event?.values!![0] +
                ", [y] = " + event.values!![1] +
                ", [z] = " + event.values!![2]

        logger.writeLog(logMessage)
        Thread.sleep(SENSOR_ACCURACY_MILLIS)
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        println(accuracy);
    }
}