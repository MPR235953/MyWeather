package com.example.myweather

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class WeatherActivity : AppCompatActivity(){
    private var pointer: Int = 0
    private val fragments = mutableMapOf(
        0 to FragmentWeatherBasicInfo(),
        1 to FragmentWeatherExtraInfo(),
        2 to FragmentWeather3Days(),
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather)

        replaceFragment()
    }

    private fun replaceFragment(){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_container_view, fragments[pointer]!!)
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
}