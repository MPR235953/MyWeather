package com.example.myweather

import android.os.AsyncTask
import org.json.JSONObject
import java.net.URL
import java.text.SimpleDateFormat
import java.util.Date

class WeatherData : java.io.Serializable{
    var CITY: String
    private val API_KEY: String = "42f9f62e465cc1855d6cd834e0f11440"

    constructor(CITY: String){
        this.CITY = CITY
    }

    inner class DataLoader : AsyncTask<String, Void, String>() {

        override fun doInBackground(vararg params: String?): String? {
            var result: String?
            try {
                result = URL("https://api.openweathermap.org/data/2.5/weather?q=$CITY&units=metric&appid=$API_KEY").readText(Charsets.UTF_8)
            }
            catch (e: Exception) {
                result = null
            }
            return result
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
        }
    }

}