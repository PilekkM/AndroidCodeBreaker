package com.example.myapplication

import android.os.Bundle
import android.widget.Button
import android.widget.Switch
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.code.CodeChecker
import com.example.myapplication.code.CodeChecker.Companion.NON_VALID_MSG
import com.example.myapplication.code.CodeChecker.Companion.VALID_MSG
import com.example.myapplication.sensors.AbstractSensor
import com.example.myapplication.sensors.GravitySensor
import com.example.myapplication.sensors.GyroscopeSensor
import com.example.myapplication.utils.PermissionUtils.Companion.checkStoragePermissions
import com.example.myapplication.utils.PermissionUtils.Companion.isStoragePermissionGranted

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        checkStoragePermissions(this)

        val checkCodeButton = findViewById<Button>(R.id.button)
        val refreshCodeButton = findViewById<Button>(R.id.refresh_code_button)
        val passwordTextView = findViewById<TextView>(R.id.edit_text_number_password)
        val showPasswordTextView = findViewById<TextView>(R.id.code_text_view)
        val codeChecker = CodeChecker(showPasswordTextView)

        checkCodeButton.setOnClickListener {
            Toast.makeText(
                this,
                if (codeChecker.isCodeCorrect(passwordTextView.text)) VALID_MSG else NON_VALID_MSG,
                Toast.LENGTH_SHORT
            ).show()
        }

        val gravitySensorThread = GravitySensor(this)
        val gravitySensorSwitch = findViewById<Switch>(R.id.gravity_switch)

        gravitySensorSwitch.setOnCheckedChangeListener { _, isChecked ->
            runSensorThread(gravitySensorSwitch, gravitySensorThread, isChecked)
        }

        val gyroscopeThread = GyroscopeSensor(this)
        val gyroscopeSwitch = findViewById<Switch>(R.id.gyro_switch)

        gyroscopeSwitch.setOnCheckedChangeListener { _, isChecked ->
            runSensorThread(gyroscopeSwitch, gyroscopeThread, isChecked)
        }

        refreshCodeButton.setOnClickListener {
            codeChecker.refreshCode()
            gravitySensorThread.updateFilename()
            gyroscopeThread.updateFilename()
        }

        val allSensorSwitch = findViewById<Switch>(R.id.all_sensor_switch)

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
