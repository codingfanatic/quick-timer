package com.codingfanatic.quicktimer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private var timeDisplayed: TextView? = null
    private var timerButton: Button? = null
    private var someTimer: CountDownTimer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        object : CountDownTimer(30000, 1000) {

            override fun onTick(millisUntilFinished: Long) {
                //mTextField.setText("seconds remaining: " + millisUntilFinished / 1000)
            }

            override fun onFinish() {
                //mTextField.setText("done!")
            }
        }.start()


    }
}


