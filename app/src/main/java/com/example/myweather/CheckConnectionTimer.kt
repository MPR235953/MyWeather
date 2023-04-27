package com.example.myweather

import android.os.CountDownTimer
import androidx.appcompat.app.AppCompatActivity

class CheckConnectionTimer {
    companion object {
        var context: AppCompatActivity? = null

        // check connection status timer (15 sec)
        var countDownTimer = object : CountDownTimer(15000, 1000) {
            override fun onTick(millisUntilFinished: Long) {}
            override fun onFinish() {
                if(context?.let { isOnline(it) } == true) setStatus(false)
                else setStatus(true)
                start()
            }
        }

        fun start(context: AppCompatActivity) {
            this.context = context
            countDownTimer.start()
        }

        fun cancel() {
            countDownTimer.cancel()
        }
    }
}