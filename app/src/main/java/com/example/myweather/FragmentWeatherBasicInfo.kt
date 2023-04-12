package com.example.myweather

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment


class FragmentWeatherBasicInfo : Fragment() {
    lateinit var tvTime: TextView
    lateinit var thisView: View
    lateinit var weatherParser: WeatherParser
    var test = 0
    // TODO: Initialize to use in other activities
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        thisView = inflater.inflate(R.layout.fragemnt_weather_basic_info, container, false)
        (thisView.findViewById(R.id.tvTime) as TextView).setText(WeatherParser.time)
        (thisView.findViewById(R.id.tvCords) as TextView).setText(weatherParser.cords[0].toBigDecimal().toPlainString()+ ", " + weatherParser.cords[1].toBigDecimal().toPlainString())
        (thisView.findViewById(R.id.tvTemp) as TextView).setText(weatherParser.temp.toBigDecimal().toPlainString())
        (thisView.findViewById(R.id.tvPress) as TextView).setText(weatherParser.press.toBigDecimal().toPlainString())
        return thisView
    }

    @JvmName("setWeatherParser1")
    fun setWeatherParser(weatherParser: WeatherParser){
        this.weatherParser = weatherParser
    }

    fun update(){
        (thisView.findViewById(R.id.tvTime) as TextView).setText(WeatherParser.time)
        (thisView.findViewById(R.id.tvCords) as TextView).setText(weatherParser.cords[0].toBigDecimal().toPlainString()+ ", " + weatherParser.cords[1].toBigDecimal().toPlainString())
        (thisView.findViewById(R.id.tvTemp) as TextView).setText(weatherParser.temp.toBigDecimal().toPlainString())
        (thisView.findViewById(R.id.tvPress) as TextView).setText(weatherParser.press.toBigDecimal().toPlainString())
    }
}