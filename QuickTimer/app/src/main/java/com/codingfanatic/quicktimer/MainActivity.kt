package com.codingfanatic.quicktimer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.widget.Button
import android.widget.TextView
import com.codingfanatic.quicktimer.databinding.ActivityMainBinding

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


//        serviceIntent = Intent()  ----------------- Will pick up from here next time 5m30s
//        registerReceiver()

        initViews()

//        object : CountDownTimer(30000, 1000) {
//
//            override fun onTick(millisUntilFinished: Long) {
//                //mTextField.setText("seconds remaining: " + millisUntilFinished / 1000)
//            }
//
//            override fun onFinish() {
//                //mTextField.setText("done!")
//            }
//        }.start()


    }

    private fun resetTimer() {
        TODO("Not yet implemented")
    }

    private fun startStopTimer() {
        TODO("Not yet implemented")
    }

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


