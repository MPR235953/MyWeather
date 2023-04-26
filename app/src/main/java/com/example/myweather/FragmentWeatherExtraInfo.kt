package com.example.myweather

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import org.json.JSONObject

class FragmentWeatherExtraInfo : MyFragment() {
    lateinit var thisView: View

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        thisView = inflater.inflate(R.layout.fragemnt_weather_extra_info, container, false)
        update()
        return thisView
    }

    override fun update(){
        val activity: WeatherActivity = activity as WeatherActivity
        val jsonData = JSONObject(activity.CITY_WEATHER_DATA)
        val settingsMap = JSONObject(activity.WEATHER_SETTINGS)

        var windForce = jsonData.getJSONArray("list").getJSONObject(0).getJSONObject("wind").getString("speed")
        val windDirection = jsonData.getJSONArray("list").getJSONObject(0).getJSONObject("wind").getString("deg")
        val humidity = jsonData.getJSONArray("list").getJSONObject(0).getJSONObject("main").getString("humidity")

        if(windForce.split(" ")[1] != settingsMap["windForce_unit"].toString()[0].toString()){
            if(settingsMap["windForce_unit"] == "km/h")
                windForce = (windForce.split(" ")[0].toDouble() * 1000 / 3600).toString() + " m/s"
            else
                windForce = (windForce.split(" ")[0].toDouble() * 3600 / 1000).toString() + " km/h"
        }

        (thisView.findViewById(R.id.tvWindForce) as TextView).setText(windForce)
        (thisView.findViewById(R.id.tvWindDirection) as TextView).setText(windDirection)
        (thisView.findViewById(R.id.tvHumidity) as TextView).setText(humidity)
    }
}