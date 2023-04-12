package com.example.myweather

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment


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
        (thisView.findViewById(R.id.tvTime) as TextView).setText(WeatherParser.time)
        (thisView.findViewById(R.id.tvCords) as TextView).setText(WeatherParser.cords[0].toBigDecimal().toPlainString()+ ", " + WeatherParser.cords[1].toBigDecimal().toPlainString())
        (thisView.findViewById(R.id.tvTemp) as TextView).setText(WeatherParser.temp.toBigDecimal().toPlainString())
        (thisView.findViewById(R.id.tvPress) as TextView).setText(WeatherParser.press.toBigDecimal().toPlainString())
    }
}