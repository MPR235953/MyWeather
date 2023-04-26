package com.example.myweather

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class FavoritesActivity : AppCompatActivity() {
    lateinit var etCityToAdd: EditText
    lateinit var weatherParser: WeatherParser
    var favoriteCities = mutableListOf<LinearLayout>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorites)
        weatherParser = getIntent().getSerializableExtra("weather_parser") as WeatherParser
        etCityToAdd = findViewById(R.id.etCityToAdd)
    }

    fun addCity(view: View){
        var city: String = etCityToAdd.getText().toString()

        val parentLayout = findViewById<LinearLayout>(R.id.linearLayout0)
        val inflater = LayoutInflater.from(this)
        val subLayout = inflater.inflate(R.layout.element_favorite_city, parentLayout, false)
        parentLayout.addView(subLayout)
        

        //val parentLayout = findViewById<LinearLayout>(R.id.linearLayout0)

        //val layout = LinearLayout(this)
        //android:layout_width="match_parent"
        //android:layout_weight="1"
        //android:layout_height="0dp"
        //android:background="#ddd"
        //layout.layoutParams = LinearLayout.LayoutParams(
        //    LinearLayout.LayoutParams.MATCH_PARENT,
        //    LinearLayout.LayoutParams.WRAP_CONTENT
        //)

        //parentLayout.addView(layout)
    }

    fun delCity(view: View){


    }
}