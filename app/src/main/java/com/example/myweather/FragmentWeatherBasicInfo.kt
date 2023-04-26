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
        val jsonData = JSONObject(activity.WEATHER_DATA)

        val time = SimpleDateFormat("hh:mm:ss").format(Date())
        val cordLon = jsonData.getJSONObject("coord").getDouble("lon")
        val cordLat = jsonData.getJSONObject("coord").getDouble("lat")
        val temp = jsonData.getJSONObject("main").getDouble("temp")
        val press = jsonData.getJSONObject("main").getDouble("pressure")

        (thisView.findViewById(R.id.tvTime) as TextView).setText(time)
        (thisView.findViewById(R.id.tvCords) as TextView).setText(cordLon.toBigDecimal().toPlainString()+ ", " + cordLat.toBigDecimal().toPlainString())
        (thisView.findViewById(R.id.tvTemp) as TextView).setText(temp.toBigDecimal().toPlainString())
        (thisView.findViewById(R.id.tvPress) as TextView).setText(press.toBigDecimal().toPlainString())
    }
}