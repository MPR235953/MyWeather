package com.example.myweather

import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible

class WeatherActivity : AppCompatActivity(){
    lateinit var tvStatus: TextView
    lateinit var weatherParser: WeatherParser
    lateinit var test:FragmentWeatherBasicInfo

    private var pointer: Int = 0
    private val fragments = arrayOf(
        FragmentWeatherBasicInfo(),
        FragmentWeatherExtraInfo(),
        FragmentWeather3Days()
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather)

        weatherParser = getIntent().getSerializableExtra("weather_parser") as WeatherParser


        replaceFragment()
        //update()
    }

    private fun replaceFragment(){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        //fragments[0].setWeatherParser(weatherParser)
        test = FragmentWeatherBasicInfo()
        test.setWeatherParser(weatherParser)
        fragmentTransaction.replace(R.id.fragment_container_view, test)
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
            tvStatus.isVisible = false
            test.update()
        }
    }

    fun update(){
        (findViewById(R.id.tvCity) as TextView).setText(weatherParser.city)
        (FragmentWeatherBasicInfo().thisView.findViewById(R.id.tvTime) as TextView).setText(WeatherParser.time)
        (findViewById(R.id.tvCords) as TextView).setText((weatherParser.cords[0] as String) + ", " + (weatherParser.cords[1] as String))
        (findViewById(R.id.tvTemp) as TextView).setText(weatherParser.temp as String)
        (findViewById(R.id.tvPress) as TextView).setText(weatherParser.press as String)
        (findViewById(R.id.tvWindForce) as TextView).setText(weatherParser.windForce as String)
        (findViewById(R.id.tvWindDirection) as TextView).setText(weatherParser.windDirection as String)
        (findViewById(R.id.tvHumidity) as TextView).setText(weatherParser.humidity as String)
    }
}