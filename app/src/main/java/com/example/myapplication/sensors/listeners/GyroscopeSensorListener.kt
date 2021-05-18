package com.example.myapplication.sensors.listeners

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import com.example.myapplication.config.Configuration
import com.example.myapplication.log.Logger
import com.example.myapplication.utils.TimeUtils

class GyroscopeSensorListener : SensorEventListener{

    //TODO think of abstract class/interface for Sensors
    private val logger = Logger(Configuration.GYROSCOPE_SENSOR_LOG_FILENAME)

    override fun onSensorChanged(event: SensorEvent?) {
        val logMessage = TimeUtils.getActualTimestamp(TimeUtils.ONLY_FULL_TIME_PATTERN) +
                "[x] = " + event?.values!![0] +
                ", [y] = " + event.values!![1] +
                ", [z] = " + event.values!![2]

        logger.writeLog(logMessage)
        Thread.sleep(Configuration.SENSOR_ACCURACY_MILLIS)
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        println(accuracy);
    }

}