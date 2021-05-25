package com.example.myapplication.sensors

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Handler
import android.os.HandlerThread

abstract class AbstractSensor(context: Context) : Runnable {


    private var mContext: Context? = context
    private var mSensorManager: SensorManager? = null
    private var mSensor: Sensor? = null
    private var mListener: SensorEventListener? = null
    private var mHandlerThread: HandlerThread? = null

    override fun run() {
        mSensorManager = mContext?.getSystemService(Context.SENSOR_SERVICE) as SensorManager
        mSensor = mSensorManager?.getDefaultSensor(getSensorType())
        mHandlerThread = HandlerThread(getThreadName())
        mHandlerThread!!.start()

        val handler = Handler(mHandlerThread!!.looper)

        mListener = getListener()

        mSensorManager!!.registerListener(
            mListener,
            mSensor,
            SensorManager.SENSOR_DELAY_FASTEST,
            handler
        )
    }

    abstract fun getListener(): SensorEventListener?

    abstract fun getThreadName(): String?

    abstract fun getSensorType(): Int


    fun cleanThread() {
        if (mSensorManager != null) {
            mSensorManager!!.unregisterListener(mListener)
        }

        if (mHandlerThread!!.isAlive) {
            mHandlerThread!!.quitSafely()
        }
    }
}