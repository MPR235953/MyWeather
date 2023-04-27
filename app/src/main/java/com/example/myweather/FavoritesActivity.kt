package com.example.myweather

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible

class FavoritesActivity : AppCompatActivity() {
    companion object {
        @SuppressLint("StaticFieldLeak")
        var tvStatus: TextView? = null
    }

    lateinit var etCityToAdd: EditText
    lateinit var CITY: String
    lateinit var CITY_WEATHER_DATA: String
    lateinit var sharedPreferences: SharedPreferences
    lateinit var FAVORITE_CITIES_WEATHER_DATA: Map<String, *>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorites)

        etCityToAdd = findViewById(R.id.etCityToAdd)
        tvStatus = findViewById(R.id.tvStatus)

        sharedPreferences = getSharedPreferences("FAVORITE_CITIES_WEATHER_DATA", Context.MODE_PRIVATE)
        FAVORITE_CITIES_WEATHER_DATA = sharedPreferences.all
        for ((city, city_weather_data) in FAVORITE_CITIES_WEATHER_DATA) {
            addCityButton(city, city_weather_data as String)
        }
    }

    fun addCityButton(city: String, city_weather_data: String){
        // configure add button
        val parentLayout = findViewById<LinearLayout>(R.id.linearLayout0)
        val btnCity: Button = Button(this)
        btnCity.text = city
        btnCity.setOnClickListener {
            val intent: Intent = Intent(this, WeatherActivity::class.java)
            intent.putExtra("CITY_WEATHER_DATA", city_weather_data)
            startActivity(intent)
        }
        parentLayout.addView(btnCity)
    }

    fun addCity(view: View){
        // get weather data
        CITY = etCityToAdd.getText().toString()
        CITY_WEATHER_DATA = WeatherData(CITY).DataLoader().execute().get()
        if(!isOnline(this)){
            val toast = Toast.makeText(applicationContext, "No internet connection", Toast.LENGTH_SHORT)
            toast.show()
            tvStatus?.let { toggleStatue(true) }
        }
        else{
            tvStatus?.let { toggleStatue(false) }

            if(CITY_WEATHER_DATA == "error"){
                val toast = Toast.makeText(applicationContext, "Incorrect city name", Toast.LENGTH_SHORT)
                toast.show()
            }
            else{
                // save data on device
                sharedPreferences = getSharedPreferences("FAVORITE_CITIES_WEATHER_DATA", Context.MODE_PRIVATE)
                val editor = sharedPreferences.edit()
                editor.putString(CITY, CITY_WEATHER_DATA)
                editor.apply()

                // add city button
                addCityButton(CITY, CITY_WEATHER_DATA)
            }
        }
    }

    fun clearAll(view: View){
        // clear device memory
        val editor = sharedPreferences.edit()
        editor.clear()
        editor.apply()

        // clear views from layout
        val parentLayout = findViewById<LinearLayout>(R.id.linearLayout0)
        parentLayout.removeAllViews()
    }
}