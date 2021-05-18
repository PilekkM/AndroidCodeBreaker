package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Switch
import android.widget.TextView
import android.widget.Toast
import com.example.myapplication.sensors.GravitySensor
import com.example.myapplication.sensors.GyroscopeSensor
import com.example.myapplication.utils.CodeCheckUtility.Companion.NON_VALID_MSG
import com.example.myapplication.utils.CodeCheckUtility.Companion.VALID_MSG
import com.example.myapplication.utils.CodeCheckUtility.Companion.isPasswordCorrect
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
        val gravitySensorSwitch = findViewById<Switch>(R.id.gravity_switch)
        val gyroscopeSwitch = findViewById<Switch>(R.id.gyro_switch)
        val allSensorSwitch = findViewById<Switch>(R.id.all_sensor_switch)
        val passwordTextView = findViewById<TextView>(R.id.editTextNumberPassword)
        gravitySensorThread = GravitySensor(this)
        gyroscopeThread = GyroscopeSensor(this)

        checkCodeButton.setOnClickListener {
            Toast.makeText(
                this,
                if (isPasswordCorrect(passwordTextView.text)) VALID_MSG else NON_VALID_MSG,
                Toast.LENGTH_SHORT
            ).show()
        }

        //TODO extract common logic from setOnCheckedListener
        gravitySensorSwitch.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                if (isStoragePermissionGranted(this)) {
                    gravitySensorThread!!.run()
                } else {
                    Toast.makeText(
                        this,
                        "Please give this application permission for writing to storage",
                        Toast.LENGTH_SHORT
                    ).show()
                    gravitySensorSwitch.isChecked = false
                }
            } else {
                gravitySensorThread!!.cleanThread()
            }
        }

        gyroscopeSwitch.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                if (isStoragePermissionGranted(this)) {
                    gyroscopeThread!!.run()
                } else {
                    Toast.makeText(
                        this,
                        "Please give this application permission for writing to storage",
                        Toast.LENGTH_SHORT
                    ).show()
                    gravitySensorSwitch.isChecked = false
                }
            } else {
                gyroscopeThread!!.cleanThread()
            }
        }

        allSensorSwitch.setOnCheckedChangeListener { _, isChecked ->
            if(isChecked){
                gyroscopeSwitch.isChecked = true
                gravitySensorSwitch.isChecked = true
            }else{
                gyroscopeSwitch.isChecked = false
                gravitySensorSwitch.isChecked = false
            }

        }

    }
}
