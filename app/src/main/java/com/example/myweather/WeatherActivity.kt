package com.example.myweather

import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible

class WeatherActivity : AppCompatActivity(){
    lateinit var tvStatus: TextView

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

    fun refresh(view: View){
        tvStatus = findViewById(R.id.tvStatus)
        if(!isOnline(this)) {
            val toast = Toast.makeText(applicationContext, "No internet connection", Toast.LENGTH_SHORT)
            toast.show()
            tvStatus.isVisible = true
        }
        else tvStatus.isVisible = false
    }
}