package com.example.myweather

import android.annotation.SuppressLint
import android.os.AsyncTask
import org.json.JSONObject
import java.net.URL
import java.text.SimpleDateFormat
import java.util.Date

class WeatherParser : java.io.Serializable{
    var city: String
    private var api_key: String

    companion object {
        // Basic
        var time: String = ""
        var cords = arrayOf(0.0, 0.0)
        var temp: Double = 0.0
        var press: Double = 0.0

        // Extra
        var windForce: Double = 0.0
        var windDirection: Double = 0.0
        var humidity: Double = 0.0
    }

    constructor(city: String, api_key:String){
        this.city = city
        this.api_key = api_key
    }

    inner class weatherTask : AsyncTask<String, Void, String>() {

        override fun doInBackground(vararg params: String?): String? {
            var result: String?
            try {
                result =
                    URL("https://api.openweathermap.org/data/2.5/weather?q=$city&units=metric&appid=$api_key").readText(
                        Charsets.UTF_8
                    )
            } catch (e: Exception) {
                result = null
                time = e.toString()
            }
            return result
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)

            val jsonData = JSONObject(result!!)

            time = SimpleDateFormat("hh:mm").format(Date())
            cords[0] = jsonData.getJSONObject("coord").getDouble("lon")
            cords[1] = jsonData.getJSONObject("coord").getDouble("lat")
            temp = jsonData.getJSONObject("main").getDouble("temp")
            press = jsonData.getJSONObject("main").getDouble("pressure")

            windForce = jsonData.getJSONObject("wind").getDouble("speed")
            windDirection = jsonData.getJSONObject("wind").getDouble("deg")
            humidity = jsonData.getJSONObject("main").getDouble("humidity")
        }
    }

}