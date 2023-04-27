package com.example.myweather

import android.annotation.SuppressLint
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class SettingsActivity : AppCompatActivity() {
    companion object {
        @SuppressLint("StaticFieldLeak")
        var tvStatus: TextView? = null
    }

    lateinit var sTemp: Spinner
    lateinit var sTime: Spinner
    lateinit var sPress: Spinner
    lateinit var sCords: Spinner
    lateinit var sWindForce: Spinner
    lateinit var sWindDirection: Spinner
    lateinit var sHumidity: Spinner
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        tvStatus = findViewById(R.id.tvStatus)

        sTemp = findViewById(R.id.sTemp)
        ArrayAdapter.createFromResource(this,R.array.saTemp,android.R.layout.simple_spinner_item).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            sTemp.adapter = adapter
        }

        sTime = findViewById(R.id.sTime)
        ArrayAdapter.createFromResource(this,R.array.saTime,android.R.layout.simple_spinner_item).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            sTime.adapter = adapter
        }

        sPress = findViewById(R.id.sPress)
        ArrayAdapter.createFromResource(this,R.array.saPress,android.R.layout.simple_spinner_item).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            sPress.adapter = adapter
        }

        sCords = findViewById(R.id.sCords)
        ArrayAdapter.createFromResource(this,R.array.saCords,android.R.layout.simple_spinner_item).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            sCords.adapter = adapter
        }

        sWindForce = findViewById(R.id.sWindForce)
        ArrayAdapter.createFromResource(this,R.array.saWindForce,android.R.layout.simple_spinner_item).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            sWindForce.adapter = adapter
        }

        sWindDirection = findViewById(R.id.sWindDirection)
        ArrayAdapter.createFromResource(this,R.array.saWindDirection,android.R.layout.simple_spinner_item).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            sWindDirection.adapter = adapter
        }

        sHumidity = findViewById(R.id.sHumidity)
        ArrayAdapter.createFromResource(this,R.array.saHumidity,android.R.layout.simple_spinner_item).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            sHumidity.adapter = adapter
        }
    }

    fun saveSettings(view: View){
        // save data on device
        val sharedPreferences: SharedPreferences = getSharedPreferences("WEATHER_SETTINGS", MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("temp_unit", sTemp.selectedItem.toString())
        editor.putString("time_unit", sTime.selectedItem.toString())
        editor.putString("press_unit", sPress.selectedItem.toString())
        editor.putString("cords_unit", sCords.selectedItem.toString())
        editor.putString("windForce_unit", sWindForce.selectedItem.toString())
        editor.putString("windDirection_unit", sWindDirection.selectedItem.toString())
        editor.putString("humidity_unit", sHumidity.selectedItem.toString())
        editor.apply()

        // show toast
        val toast = Toast.makeText(applicationContext, "Settings were saved", Toast.LENGTH_SHORT)
        toast.show()
    }
}