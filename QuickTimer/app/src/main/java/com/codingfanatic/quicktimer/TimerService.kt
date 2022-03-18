package com.codingfanatic.quicktimer

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import java.util.*

class TimerService: Service() {

    override fun onBind(p0: Intent?): IBinder? = null

    private val timer = Timer()


    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        val time = intent.getDoubleExtra(TIME_EXTRA, 0.0)
        timer.scheduleAtFixedRate(TimeTask(time), 0, 1)
        return START_NOT_STICKY
    }

    override fun onDestroy(){
        timer.cancel()
        super.onDestroy()
    }

    private inner class TimeTask(private var time: Double) : TimerTask() {
        override fun run() {
            val intent = Intent(TIMER_UPDATED)
            intent.putExtra(TIME_EXTRA, time)
            time += .001
            sendBroadcast(intent)
            Log.v("Look at the time", "${time}")
        }
    }

    companion object {
        const val TIMER_UPDATED = "timerUpdated"
        const val TIME_EXTRA = "timeExtra"
    }
}