package com.codingfanatic.quicktimer

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.codingfanatic.quicktimer.databinding.ActivityMainBinding
import kotlin.math.roundToInt
import kotlin.math.truncate

class MainActivityOLD : AppCompatActivity() {

    companion object {
        const val TAG = "MainActivity"
    }
    private var timeDisplayed: TextView? = null
    private var timerButton: Button? = null
    private var someTimer: CountDownTimer? = null

    private var timerStarted = false

    private lateinit var serviceIntent: Intent
    private lateinit var binding: ActivityMainBinding
    private var time = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.startStopButton.setOnClickListener{ startStopTimer() }
        binding.resetButton.setOnClickListener{ resetTimer() }


        serviceIntent = Intent(applicationContext, TimerService::class.java)
        registerReceiver(updateTime, IntentFilter(TimerService.TIMER_UPDATED))
    }

    private fun resetTimer() {
        stopTimer()
        time = 0.0
        binding.timerTextView.text = getTimeStringFromDouble(time)
    }

    private fun startStopTimer() {
        if(timerStarted){
            stopTimer()
        }
        else{
            startTimer()
        }
    }

    private fun startTimer() {
        serviceIntent.putExtra(TimerService.TIME_EXTRA, time)
        startService(serviceIntent)
        binding.startStopButton.text = "Stop"
        binding.startStopButton.icon = getDrawable(R.drawable.ic_baseline_pause_24)
        binding.timerTextView.text = getTimeStringFromDouble(time)
        timerStarted = true
    }

    private fun stopTimer() {
        stopService(serviceIntent)
        binding.startStopButton.text = "Start"
        binding.startStopButton.icon = getDrawable(R.drawable.ic_baseline_play_arrow_24)
        timerStarted = false
    }

    private val updateTime: BroadcastReceiver = object: BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            time = intent.getDoubleExtra(TimerService.TIME_EXTRA, 0.0)
            binding.timerTextView.text = getTimeStringFromDouble(time)
        }
    }

    private fun getTimeStringFromDouble(time: Double): String {
        val resultInt = truncate(time).toInt()
        val hours = resultInt % 86400 / 3600
        val minutes = resultInt % 86400 % 3600 / 60
        val seconds = resultInt % 86400 % 3600 % 60

        return makeTimeString(hours, minutes, seconds)
    }

    private fun makeTimeString(hour: Int, min: Int, sec: Int): String = String.format("%02d:%02d:%02d", hour, min, sec)
}


