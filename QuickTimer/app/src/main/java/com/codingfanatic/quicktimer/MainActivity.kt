package com.codingfanatic.quicktimer

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.SeekBar
import android.widget.TextView
import com.codingfanatic.quicktimer.databinding.ActivityMainBinding
import com.codingfanatic.quicktimer.databinding.SecondMainBinding
import kotlin.math.roundToInt
import kotlin.math.truncate

class MainActivity: AppCompatActivity() {
    var timer_seekBar: SeekBar? = null
    var timer_textView_two: TextView? =null
    var timer_button_two: Button? =null
    var countDownTimer: CountDownTimer? =null
    var counterIsActive = false
    var mediaPlayer: MediaPlayer? =null

    private lateinit var binding: SecondMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        binding = SecondMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.startStopButtonTwo.setOnClickListener{ start_timer() }
        mediaPlayer = MediaPlayer.create(applicationContext, R.raw.alarm)

        binding.volumeSeekBar.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                update(progress)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                TODO("Not yet implemented")
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                TODO("Not yet implemented")
            }

        })
        /*
            serviceIntent = Intent(applicationContext, TimerService::class.java)
            registerReceiver(updateTime, IntentFilter(TimerService.TIMER_UPDATED))
        */
    }

    private fun update(progress: Int){
        val minutes = progress / 60
        val seconds = progress % 60
        val secondsFinal = ""

        if(seconds <= 9){
            secondsFinal.plus("0$seconds")
        }
        else{
            secondsFinal.plus(seconds)
        }
        timer_seekBar?.setProgress(progress)
        timer_textView_two?.setText("$minutes:$secondsFinal")
    }





    private fun start_timer(view: View) {
        if(!counterIsActive){
            counterIsActive = true;
            timer_seekBar?.setEnabled(false)
            //Also set start button text to stop...once we figure out where it is?
        }
    }
}