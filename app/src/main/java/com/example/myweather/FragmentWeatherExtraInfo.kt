package com.example.myweather

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

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
        (thisView.findViewById(R.id.tvWindForce) as TextView).setText(WeatherParser.windForce.toBigDecimal().toPlainString())
        (thisView.findViewById(R.id.tvWindDirection) as TextView).setText(WeatherParser.windDirection.toBigDecimal().toPlainString())
        (thisView.findViewById(R.id.tvHumidity) as TextView).setText(WeatherParser.humidity.toBigDecimal().toPlainString())
    }
}