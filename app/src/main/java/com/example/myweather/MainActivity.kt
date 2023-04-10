package com.example.myweather

import android.annotation.SuppressLint
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import android.widget.TextView
import java.net.URL

class MainActivity : AppCompatActivity() {

    val API_KEY: String = "42f9f62e465cc1855d6cd834e0f11440"
    lateinit var city: String
    lateinit var tvConnStatus: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tvConnStatus = findViewById(R.id.connection_status)
        weatherTask().execute()
    }

    @SuppressLint("StaticFieldLeak")
    inner class weatherTask : AsyncTask<String, Void, String>() {

        @Deprecated("Deprecated in Java")
        override fun doInBackground(vararg params: String?): String? {
            var response: String?
            try {
                response =
                    URL("https://api.openweathermap.org/data/2.5/weather?q=$city&units=metric&appid=$API_KEY").readText(
                        Charsets.UTF_8
                    )
            } catch (e: Exception) {
                response = null
            }
            return response
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            tvConnStatus.setText("OK")
        }
    }
}