package com.example.myweather

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity

class SettingsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        val sTemp: Spinner = findViewById(R.id.sTemp)
        ArrayAdapter.createFromResource(this,R.array.saTemp,android.R.layout.simple_spinner_item).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            sTemp.adapter = adapter
        }

        val sTime: Spinner = findViewById(R.id.sTime)
        ArrayAdapter.createFromResource(this,R.array.saTime,android.R.layout.simple_spinner_item).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            sTime.adapter = adapter
        }

        val sPress: Spinner = findViewById(R.id.sPress)
        ArrayAdapter.createFromResource(this,R.array.saPress,android.R.layout.simple_spinner_item).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            sPress.adapter = adapter
        }

        val sCords: Spinner = findViewById(R.id.sCords)
        ArrayAdapter.createFromResource(this,R.array.saCords,android.R.layout.simple_spinner_item).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            sCords.adapter = adapter
        }

        val sWindForce: Spinner = findViewById(R.id.sWindForce)
        ArrayAdapter.createFromResource(this,R.array.saWindForce,android.R.layout.simple_spinner_item).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            sWindForce.adapter = adapter
        }

        val sWindDirection: Spinner = findViewById(R.id.sWindDirection)
        ArrayAdapter.createFromResource(this,R.array.saWindDirection,android.R.layout.simple_spinner_item).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            sWindDirection.adapter = adapter
        }

        val sHumidity: Spinner = findViewById(R.id.sHumidity)
        ArrayAdapter.createFromResource(this,R.array.saHumidity,android.R.layout.simple_spinner_item).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            sHumidity.adapter = adapter
        }
    }
}