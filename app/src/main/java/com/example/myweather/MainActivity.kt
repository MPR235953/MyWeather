package com.example.myweather

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible


class MainActivity : AppCompatActivity() {
    var CITY: String = "Łódź"
    lateinit var tvStatus: TextView
    lateinit var etCity: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d("INFO", "started")

        etCity = findViewById(R.id.etCity)

        tvStatus = findViewById(R.id.tvStatus)
        if(!isOnline(this)) {
            val toast = Toast.makeText(applicationContext, "No internet connection", Toast.LENGTH_SHORT)
            toast.show()
            tvStatus.isVisible = true
        }
        else tvStatus.isVisible = false
    }

    fun toWeather(view: View){
        val intent: Intent = Intent(this, WeatherActivity::class.java)
        CITY = etCity.getText().toString()

        intent.putExtra("CITY", CITY)
        intent.putExtra("WEATHER_DATA", WeatherData(CITY).DataLoader().execute().get())

        startActivity(intent)
    }

    fun toFavorites(view: View){
        val intent: Intent = Intent(this, FavoritesActivity::class.java)
        startActivity(intent)
    }

    fun toSettings(view: View){
        val intent: Intent = Intent(this, SettingsActivity::class.java)
        startActivity(intent)
    }
}