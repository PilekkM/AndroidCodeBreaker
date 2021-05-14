package com.example.myapplication

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.myapplication.utils.CodeCheckUtility.Companion.NON_VALID_MSG
import com.example.myapplication.utils.CodeCheckUtility.Companion.VALID_MSG
import com.example.myapplication.utils.CodeCheckUtility.Companion.isPasswordCorrect

class MainActivity : AppCompatActivity(), SensorEventListener {

    private lateinit var sensorManager: SensorManager
    private var sensor: Sensor? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val checkCodeButton = findViewById<Button>(R.id.button);
        val passwordTextView = findViewById<TextView>(R.id.editTextNumberPassword);

        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY)

        checkCodeButton.setOnClickListener() {
            Toast.makeText(
                this,
                if (isPasswordCorrect(passwordTextView.text)) VALID_MSG else NON_VALID_MSG,
                Toast.LENGTH_SHORT
            ).show()
        }

        //repeat(20){
        //    print("sensor im pitor")
        //    Thread.sleep(1000)
       // }

    }

    override fun onSensorChanged(event: SensorEvent?) {
        Thread.sleep(1000)
        println("[x] = " + event?.values!![0] + ", [y] = " + event?.values!![1] + ", [z] = " + event?.values!![2])
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        println(accuracy);
    }

    override fun onResume() {
        super.onResume()
        sensor?.also { light ->
            sensorManager.registerListener(this, light, SensorManager.SENSOR_DELAY_NORMAL)
        }
    }

}
