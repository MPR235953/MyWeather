package com.example.myweather

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlin.system.exitProcess


class MainActivity : AppCompatActivity() {
    companion object {
        @SuppressLint("StaticFieldLeak")
        var tvStatus: TextView? = null
    }

    var CITY: String = "Łódź"
    lateinit var CITY_WEATHER_DATA: String
    lateinit var etCity: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d("INFO", "started")

        etCity = findViewById(R.id.etCity)
        tvStatus = findViewById(R.id.tvStatus)

        if(!isOnline(this)) tvStatus?.let { setStatus(true) }

        setDefaultSettings()

        // start refreshing data
        CheckConnectionTimer.start(this)
    }

    fun toWeather(view: View){
        val intent: Intent = Intent(this, WeatherActivity::class.java)
        CITY = etCity.getText().toString()

        CITY_WEATHER_DATA = WeatherData(CITY).DataLoader().execute().get()
        if(!isOnline(this)){
            val toast = Toast.makeText(applicationContext, "No internet connection", Toast.LENGTH_SHORT)
            toast.show()
            tvStatus?.let { setStatus(true) }

            // in case lack of internet, try to find saved data on device
            val sharedPreferences = getSharedPreferences("FAVORITE_CITIES_WEATHER_DATA", Context.MODE_PRIVATE)
            val FAVORITE_CITIES_WEATHER_DATA = sharedPreferences.all
            for ((city, city_weather_data) in FAVORITE_CITIES_WEATHER_DATA) {
                if(city == CITY){
                    val intent: Intent = Intent(this, WeatherActivity::class.java)
                    intent.putExtra("CITY_WEATHER_DATA", city_weather_data as String)
                    startActivity(intent)
                    break
                }
            }

        }
        else{
            tvStatus?.let { setStatus(false) }

            if(CITY_WEATHER_DATA == "error"){
                val toast = Toast.makeText(applicationContext, "Incorrect city name", Toast.LENGTH_SHORT)
                toast.show()
            }
            else{
                intent.putExtra("CITY_WEATHER_DATA", CITY_WEATHER_DATA)
                startActivity(intent)
            }
        }
    }

    fun toFavorites(view: View){
        val intent: Intent = Intent(this, FavoritesActivity::class.java)
        startActivity(intent)
    }

    fun toSettings(view: View){
        val intent: Intent = Intent(this, SettingsActivity::class.java)
        startActivity(intent)
    }

    fun exit(view: View){
        CheckConnectionTimer.cancel()
        finish()
        exitProcess(0)
    }

    fun setDefaultSettings() {
        // check if settings exists
        val sharedPreferences: SharedPreferences = getSharedPreferences("WEATHER_SETTINGS", Context.MODE_PRIVATE)
        val WEATHER_SETTINGS: Map<String, *> = sharedPreferences.all
        if(WEATHER_SETTINGS.isEmpty()){
            // save data on device
            val editor = sharedPreferences.edit()
            editor.putString("temp_unit", "Celsius")
            editor.putString("time_unit", "12h")
            editor.putString("press_unit", "hPa")
            editor.putString("cords_unit", "lat-lon")
            editor.putString("windForce_unit", "m/s")
            editor.putString("windDirection_unit", "deg")
            editor.putString("humidity_unit", "g.m^(-3)")
            editor.apply()
        }
    }
}