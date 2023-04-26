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
        val jsonData = JSONObject(activity.WEATHER_DATA)

        val windForce = jsonData.getJSONObject("wind").getDouble("speed")
        val windDirection = jsonData.getJSONObject("wind").getDouble("deg")
        val humidity = jsonData.getJSONObject("main").getDouble("humidity")

        (thisView.findViewById(R.id.tvWindForce) as TextView).setText(windForce.toBigDecimal().toPlainString())
        (thisView.findViewById(R.id.tvWindDirection) as TextView).setText(windDirection.toBigDecimal().toPlainString())
        (thisView.findViewById(R.id.tvHumidity) as TextView).setText(humidity.toBigDecimal().toPlainString())
    }
}