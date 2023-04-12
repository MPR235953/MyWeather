package com.example.myweather

import android.annotation.SuppressLint
import android.content.Intent
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import java.net.URL

class MainActivity : AppCompatActivity() {

    val API_KEY: String = "42f9f62e465cc1855d6cd834e0f11440"
    var CITY: String = "Łódź"
    lateinit var tvStatus: TextView
    lateinit var etCity: EditText
    lateinit var weatherParser: WeatherParser

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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

        weatherParser = WeatherParser(CITY, API_KEY)
        weatherParser.weatherTask().execute().get()
        intent.putExtra("weather_parser", weatherParser)
        startActivity(intent)
    }
}