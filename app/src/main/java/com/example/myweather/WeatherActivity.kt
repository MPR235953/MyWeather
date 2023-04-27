package com.example.myweather

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.Guideline
import androidx.core.view.isVisible
import org.json.JSONObject


class WeatherActivity : AppCompatActivity(){
    companion object {
        @SuppressLint("StaticFieldLeak")
        var tvStatus: TextView? = null
    }

    lateinit var refreshDataTimer: CountDownTimer
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

        // get a tag to handle with phone or tablet
        val glTag = findViewById<Guideline>(R.id.glTag)
        val tag = glTag.tag.toString()

        tvCity = findViewById(R.id.tvCity)
        tvStatus = findViewById(R.id.tvStatus)

        if(!isOnline(this)) tvStatus?.let { setStatus(true) }

        val sharedPreferences: SharedPreferences = getSharedPreferences("WEATHER_SETTINGS", Context.MODE_PRIVATE)
        WEATHER_SETTINGS = sharedPreferences.all

        CITY_WEATHER_DATA = getIntent().getStringExtra("CITY_WEATHER_DATA").toString()
        val jsonData = JSONObject(CITY_WEATHER_DATA)
        CITY = jsonData.getJSONObject("city").getString("name")

        tvCity.setText(CITY)

        // set up fragments in appropriate way
        if(tag == "phone") replaceFragment()
        else setUpAllFragments()

        // configure refreshDataTimer
        refreshDataTimer = object : CountDownTimer(60000, 1000) {
            override fun onTick(millisUntilFinished: Long) {}
            override fun onFinish() {
                refresh()
                refreshDataTimer.start()
            }
        }
        // and start
        refreshDataTimer.start()
    }

    override fun onResume(){
        super.onResume()
        // resume timer
        refreshDataTimer.start()
    }

    override fun onPause() {
        super.onPause()
        // cancel when minimalized
        refreshDataTimer.cancel()
    }

    private fun setUpAllFragments(){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_container_view_0, fragments[0])
        fragmentTransaction.replace(R.id.fragment_container_view_1, fragments[1])
        fragmentTransaction.replace(R.id.fragment_container_view_2, fragments[2])
        fragmentTransaction.commit()
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

    fun refresh(view: View? = null){
        CITY_WEATHER_DATA = WeatherData(CITY).DataLoader().execute().get()
        if(!isOnline(this)){
            val toast = Toast.makeText(applicationContext, "No internet connection", Toast.LENGTH_SHORT)
            toast.show()
            tvStatus?.let { setStatus(true) }
        }
        else{
            tvStatus?.let { setStatus(false) }

            if(CITY_WEATHER_DATA == "error"){
                val toast = Toast.makeText(applicationContext, "Incorrect city name", Toast.LENGTH_SHORT)
                toast.show()
            }
            else fragments[pointer].update()
        }
    }
}