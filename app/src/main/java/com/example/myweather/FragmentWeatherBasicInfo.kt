package com.example.myweather

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.Date


class FragmentWeatherBasicInfo : MyFragment() {
    lateinit var thisView: View

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        thisView = inflater.inflate(R.layout.fragemnt_weather_basic_info, container, false)
        update()
        return thisView
    }

    override fun update(){
        val activity: WeatherActivity = activity as WeatherActivity
        val weatherJsonData = JSONObject(activity.CITY_WEATHER_DATA)
        val settingsMap = JSONObject(activity.WEATHER_SETTINGS)

        val time = weatherJsonData.getJSONArray("time")[0] as String
        val cordLon = weatherJsonData.getJSONObject("coord").getString("lon")
        val cordLat = weatherJsonData.getJSONObject("coord").getString("lat")
        var temp = weatherJsonData.getJSONObject("main").getString("temp")
        val press = weatherJsonData.getJSONObject("main").getString("pressure")

        if(temp.split(" ")[1] != settingsMap["temp_unit"].toString()[0].toString()){
            if(settingsMap["temp_unit"] == "Fahrenheit")
                temp = (temp.split(" ")[0].toDouble() * 9 / 5 + 32).toString() + " F"
            else
                temp = ((temp.split(" ")[0].toDouble() - 32) * 5 / 9).toString() + " C"
        }

        (thisView.findViewById(R.id.tvTime) as TextView).setText(time)
        (thisView.findViewById(R.id.tvCords) as TextView).setText(cordLon + ", " + cordLat)
        (thisView.findViewById(R.id.tvTemp) as TextView).setText(temp)
        (thisView.findViewById(R.id.tvPress) as TextView).setText(press)
    }
}