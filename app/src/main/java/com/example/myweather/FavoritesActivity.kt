package com.example.myweather

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity

class FavoritesActivity : AppCompatActivity() {
    lateinit var etCityToAdd: EditText
    lateinit var CITY: String
    lateinit var CITY_WEATHER_DATA: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorites)

        etCityToAdd = findViewById(R.id.etCityToAdd)

        //val sharedPreferences = getSharedPreferences("FAVORITE_CITIES_WEATHER_DATA", Context.MODE_PRIVATE)
        //val editor = sharedPreferences.edit()
        //editor.putString("username", "John")
        //editor.apply()
    }

    fun addCity(view: View){
        CITY = etCityToAdd.getText().toString()
        CITY_WEATHER_DATA = WeatherData(CITY).DataLoader().execute().get()
        Log.d("INFO", CITY_WEATHER_DATA)

        val parentLayout = findViewById<LinearLayout>(R.id.linearLayout0)
        val btnCity: Button = Button(this)
        btnCity.text = CITY
        btnCity.setOnClickListener {
            val intent: Intent = Intent(this, WeatherActivity::class.java)
            intent.putExtra("CITY_WEATHER_DATA", CITY_WEATHER_DATA)
            startActivity(intent)
        }
        parentLayout.addView(btnCity)

        //val parentLayout = findViewById<LinearLayout>(R.id.linearLayout0)
        //val inflater = LayoutInflater.from(this)
        //val subLayout = inflater.inflate(R.layout.element_favorite_city, parentLayout, false)
        //parentLayout.addView(subLayout)


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

    fun delCityData(view: View){

    }

    fun loadCityData(view: View){

    }
}