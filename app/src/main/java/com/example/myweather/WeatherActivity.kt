package com.example.myweather

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import org.json.JSONObject


class WeatherActivity : AppCompatActivity(){
    lateinit var tvStatus: TextView
    lateinit var tvCity: TextView

    lateinit var WEATHER_SETTINGS: Map<String, *>
    lateinit var CITY: String
    lateinit var CITY_WEATHER_DATA: String

    private var pointer: Int = 0
    private val fragments = arrayOf(
        FragmentWeatherBasicInfo(),
        FragmentWeatherExtraInfo(),
        FragmentWeather3Days()
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather)

        tvCity = findViewById(R.id.tvCity)

        val sharedPreferences: SharedPreferences = getSharedPreferences("WEATHER_SETTINGS", Context.MODE_PRIVATE)
        WEATHER_SETTINGS = sharedPreferences.all

        CITY_WEATHER_DATA = getIntent().getStringExtra("CITY_WEATHER_DATA").toString()
        val jsonData = JSONObject(CITY_WEATHER_DATA)
        CITY = jsonData.getString("name")

        tvCity.setText(CITY)
        replaceFragment()
    }

    private fun replaceFragment(){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_container_view, fragments[pointer])
        fragmentTransaction.commit()
    }

    fun next(view: View){
        if(pointer < fragments.size - 1){
            pointer++
            replaceFragment()
        }
    }

    fun prev(view: View){
        if(pointer > 0) {
            pointer--
            replaceFragment()
        }
    }

    fun refresh(view: View){
        tvStatus = findViewById(R.id.tvStatus)
        if(!isOnline(this)) {
            val toast = Toast.makeText(applicationContext, "No internet connection", Toast.LENGTH_SHORT)
            toast.show()
            tvStatus.isVisible = true
        }
        else{
            CITY_WEATHER_DATA = WeatherData(CITY).DataLoader().execute().get()
            tvStatus.isVisible = false
            fragments[pointer].update()
        }
    }
}