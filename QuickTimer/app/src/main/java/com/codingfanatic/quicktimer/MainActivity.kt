package com.codingfanatic.quicktimer

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.widget.Button
import android.widget.TextView
import com.codingfanatic.quicktimer.databinding.ActivityMainBinding
import kotlin.math.roundToInt

class MainActivity : AppCompatActivity() {

    companion object {
        const val TAG = "MainActivity"
    }
    private var timeDisplayed: TextView? = null
    private var timerButton: Button? = null
    private var someTimer: CountDownTimer? = null

    private var started = false

    private lateinit var serviceIntent: Intent
    private lateinit var binding: ActivityMainBinding
    private var time = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.startStopButton.setOnClickListener{ startStopTimer() }
        binding.startStopButton.setOnClickListener{ resetTimer() }


        serviceIntent = Intent()
        registerReceiver(updateTime, IntentFilter(TimerService.TIMER_UPDATED))
    }

    private fun resetTimer() {
        TODO("Not yet implemented")
    }

    private fun startStopTimer() {
        TODO("Not yet implemented")
    }

    private val updateTime: BroadcastReceiver = object: BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            time = intent.getDoubleExtra(TimerService.TIME_EXTRA, 0.0)
            binding.timerTextView.text = getTimeStringFromDouble(time)
        }
    }

    private fun getTimeStringFromDouble(time: Double): String {
        val resultInt = time.roundToInt()
        val hours = resultInt % 86400 / 3600
        val minutes = resultInt % 86400 % 3600 / 60
        val seconds = resultInt % 86400 % 3600 % 60

        return makeTimeString(hours, minutes, seconds)
    }

    private fun makeTimeString(hour: Int, min: Int, sec: Int): String = String.format("%02d:%02d:%02d", hour, min, sec)

    private fun initViews() {
        timeDisplayed = findViewById(R.id.timer_textView)
        //timerButton = findViewById(R.id.timer_button)
    }
//
//    private var countDownTimer = object: CountDownTimer(1000 * 30, 1000) {
//        override fun onTick(millisUntilFinished: Long) {
//            Log.d(TAG, "onTick: $(millisUntilFinished/1000f")
//        }
//    }
}


