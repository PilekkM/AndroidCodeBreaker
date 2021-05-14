package com.example.myapplication.sensors

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Handler
import android.os.HandlerThread
import com.example.myapplication.sensors.listeners.GravitySensorListener

class GravitySensor() : Runnable {

    private var mContext: Context? = null
    private var mSensorManager: SensorManager? = null
    private var mSensor: Sensor? = null
    private var mListener: SensorEventListener? = null
    private var mHandlerThread: HandlerThread? = null

    constructor(context: Context?) : this() {
        mContext = context
    }

    override fun run() {
        mSensorManager = mContext?.getSystemService(Context.SENSOR_SERVICE) as SensorManager
        mSensor = mSensorManager?.getDefaultSensor(Sensor.TYPE_GRAVITY)
        mHandlerThread = HandlerThread("AccelerometerLogListener")
        mHandlerThread!!.start()

        val handler = Handler(mHandlerThread!!.looper)

        mListener = GravitySensorListener()

        mSensorManager!!.registerListener(mListener, mSensor, SensorManager.SENSOR_DELAY_FASTEST, handler)
    }

    public fun cleanThread(){
        if(mSensorManager != null){
            mSensorManager!!.unregisterListener(mListener)
        }

        if(mHandlerThread!!.isAlive){
            mHandlerThread!!.quitSafely()
        }
    }

}