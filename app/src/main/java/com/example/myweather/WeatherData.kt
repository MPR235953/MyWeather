package com.example.myweather

import android.annotation.SuppressLint
import android.os.AsyncTask
import android.os.Build
import androidx.annotation.RequiresApi
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

        @SuppressLint("SimpleDateFormat")
        @RequiresApi(Build.VERSION_CODES.TIRAMISU)
        override fun doInBackground(vararg params: String?): String? {
            var result: String?
            try {
                result = URL("https://api.openweathermap.org/data/2.5/weather?q=$CITY&units=metric&appid=$API_KEY").readText(Charsets.UTF_8)
                val jsonData = JSONObject(result)
                jsonData.append("time", SimpleDateFormat("hh:mm:ss a").format(Date()))
                jsonData.getJSONObject("main").put("temp", jsonData.getJSONObject("main").getString("temp") + " C")
                jsonData.getJSONObject("main").put("pressure", jsonData.getJSONObject("main").getString("pressure") + " hPa")
                jsonData.getJSONObject("wind").put("speed",jsonData.getJSONObject("wind").getString("speed") + " km/h")
                jsonData.getJSONObject("wind").put("deg", jsonData.getJSONObject("wind").getString("deg") + " deg")
                jsonData.getJSONObject("main").put("humidity", jsonData.getJSONObject("main").getString("humidity") + " g.m^(-3)")
                result = jsonData.toString()
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