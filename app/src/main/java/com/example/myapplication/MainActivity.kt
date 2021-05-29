package com.example.myapplication

import android.os.Bundle
import android.widget.Button
import android.widget.Switch
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.sensors.AbstractSensor
import com.example.myapplication.sensors.GravitySensor
import com.example.myapplication.sensors.GyroscopeSensor
import com.example.myapplication.utils.CodeCheckUtility.Companion.VALID_MSG
import com.example.myapplication.utils.CodeCheckUtility.Companion.isPasswordCorrect
import com.example.myapplication.utils.CodeGenUtility.Companion.genNewPassword
import com.example.myapplication.utils.CodeGenUtility.Companion.CURRENT_PASS

import com.example.myapplication.utils.PermissionUtils.Companion.checkStoragePermissions
import com.example.myapplication.utils.PermissionUtils.Companion.isStoragePermissionGranted

class MainActivity : AppCompatActivity() {

    private var gravitySensorThread: GravitySensor? = null
    private var gyroscopeThread: GyroscopeSensor? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        checkStoragePermissions(this)

        val checkCodeButton = findViewById<Button>(R.id.button)
        val generatePassButton = findViewById<Button>(R.id.button_pass)
        val gravitySensorSwitch = findViewById<Switch>(R.id.gravity_switch)
        val gyroscopeSwitch = findViewById<Switch>(R.id.gyro_switch)
        val allSensorSwitch = findViewById<Switch>(R.id.all_sensor_switch)
        val passwordTextView = findViewById<TextView>(R.id.editTextNumberPassword)
        gravitySensorThread = GravitySensor(this)
        gyroscopeThread = GyroscopeSensor(this)

        generatePassButton.setOnClickListener {
            genNewPassword()
            Toast.makeText(
                this,
                CURRENT_PASS,
                Toast.LENGTH_SHORT
            ).show()
            passwordTextView.setText("")
            // TODO: Start SensorListeners (maybe for a fixed amount of time?)

        }
        checkCodeButton.setOnClickListener {
            Toast.makeText(
                this,
                if (isPasswordCorrect(passwordTextView.text, CURRENT_PASS))  VALID_MSG else "Incorrect code should be $CURRENT_PASS",
                Toast.LENGTH_SHORT
            ).show()
            // TODO: If password is correct, save the logs with current Timestamp
        }

        gravitySensorSwitch.setOnCheckedChangeListener { _, isChecked ->
            runSensorThread(gravitySensorSwitch, gravitySensorThread!!, isChecked)
        }

        gyroscopeSwitch.setOnCheckedChangeListener { _, isChecked ->
            runSensorThread(gyroscopeSwitch, gyroscopeThread!!, isChecked)
        }

        allSensorSwitch.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                gyroscopeSwitch.isChecked = true
                gravitySensorSwitch.isChecked = true
            } else {
                gyroscopeSwitch.isChecked = false
                gravitySensorSwitch.isChecked = false
            }

        }

    }

    private fun runSensorThread(switch: Switch, sensorThread: AbstractSensor, isChecked: Boolean) {
        if (isChecked) {
            if (isStoragePermissionGranted(this)) {
                sensorThread.run()
            } else {
                Toast.makeText(
                    this,
                    "Please give this application permission for writing to storage",
                    Toast.LENGTH_SHORT
                ).show()
                switch.isChecked = false
            }
        } else {
            sensorThread.cleanThread()
        }
    }
}
