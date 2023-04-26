package com.example.myweather

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import org.json.JSONObject

class FragmentWeather3Days : MyFragment() {
    lateinit var thisView: View

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        thisView = inflater.inflate(R.layout.fragemnt_weather_3days, container, false)
        update()
        return thisView
    }

    override fun update(){
        val activity: WeatherActivity = activity as WeatherActivity
        val weatherJsonData = JSONObject(activity.CITY_WEATHER_DATA)

        // get weather forecasts for unique days
        val forecastArray = weatherJsonData.getJSONArray("list")
        var startDate = forecastArray.getJSONObject(0).getString("dt_txt").split(" ")[0]
        val dates = mutableListOf<String>()
        val forecasts = mutableListOf<String>()
        for (i in 0 until forecastArray.length()) {
            if(dates.size == 3) break
            val newDate = forecastArray.getJSONObject(i).getString("dt_txt").split(" ")[0]
            val newForecast = forecastArray.getJSONObject(i).getJSONArray("weather").getJSONObject(0).getString("main")
            if(newDate != startDate){
                dates.add(newDate)
                forecasts.add(newForecast)
                startDate = newDate
            }
        }

        // mapping data text to appropriate images
        val resources = mutableListOf<Int>()
        for (i in 0 until forecasts.size) {
            when(forecasts[i]){
                "Clear" -> resources.add(R.drawable.clear)
                "Rain" -> resources.add(R.drawable.rain)
                "Clouds" -> resources.add(R.drawable.clouds)
                else -> resources.add(R.drawable.unknown)
            }
        }

        // set ImageViews and TextViews
        (thisView.findViewById(R.id.iv0) as ImageView).setImageResource(resources[0])
        (thisView.findViewById(R.id.iv1) as ImageView).setImageResource(resources[1])
        (thisView.findViewById(R.id.iv2) as ImageView).setImageResource(resources[2])

        (thisView.findViewById(R.id.tv0) as TextView).setText(dates[0])
        (thisView.findViewById(R.id.tv1) as TextView).setText(dates[1])
        (thisView.findViewById(R.id.tv2) as TextView).setText(dates[2])
    }
}