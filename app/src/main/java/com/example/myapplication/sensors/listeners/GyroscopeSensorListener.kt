package com.example.myapplication.sensors.listeners

import android.hardware.Sensor
import android.hardware.SensorEvent
import com.example.myapplication.config.Configuration
import com.example.myapplication.log.Logger
import com.example.myapplication.utils.TimeUtils
import com.example.myapplication.utils.TimeUtils.Companion.getActualTimestamp

class GyroscopeSensorListener : LoggingSensorEventListener {

    private val logger = Logger(
        Configuration.GYROSCOPE_SENSOR_LOG_FILENAME,
        getActualTimestamp(TimeUtils.ONLY_FULL_TIME_WITHOUT_COLONS_PATTERN)
    )

    override fun onSensorChanged(event: SensorEvent?) {
        val logMessage = getActualTimestamp(TimeUtils.ONLY_FULL_TIME_PATTERN) +
                "[x] = " + event?.values!![0] +
                ", [y] = " + event.values!![1] +
                ", [z] = " + event.values!![2]

        logger.writeLog(logMessage)
        Thread.sleep(Configuration.SENSOR_ACCURACY_MILLIS)
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        println(accuracy)
    }

    override fun updateFilename() {
        logger.updateTimestamp(getActualTimestamp(TimeUtils.ONLY_FULL_TIME_WITHOUT_COLONS_PATTERN))
    }
}