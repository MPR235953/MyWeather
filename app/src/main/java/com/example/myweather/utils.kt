package com.example.myweather

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible

fun isOnline(context: AppCompatActivity): Boolean {
    val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    if (connectivityManager != null) {
        val capabilities =
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        if (capabilities != null) {
            if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                Log.i("Internet", "NetworkCapabilities.TRANSPORT_CELLULAR")
                return true
            } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                Log.i("Internet", "NetworkCapabilities.TRANSPORT_WIFI")
                return true
            } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
                Log.i("Internet", "NetworkCapabilities.TRANSPORT_ETHERNET")
                return true
            }
        }
    }
    return false
}

fun setStatus(visibility: Boolean){
    if(MainActivity.tvStatus != null) MainActivity.tvStatus!!.isVisible = visibility
    if(WeatherActivity.tvStatus != null) WeatherActivity.tvStatus!!.isVisible = visibility
    if(FavoritesActivity.tvStatus != null) FavoritesActivity.tvStatus!!.isVisible = visibility
    if(SettingsActivity.tvStatus != null) SettingsActivity.tvStatus!!.isVisible = visibility
}