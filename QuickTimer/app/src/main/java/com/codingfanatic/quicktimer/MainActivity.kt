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

class MainActivity() : AppCompatActivity() {
    var countDownTimer: CountDownTimer? =null
    var counterIsActive = false
    var mediaPlayer: MediaPlayer? =null
    val seekBarStartingProgress = 10

    private lateinit var binding: SecondMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        binding = SecondMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.startStopButtonTwo.setOnClickListener{ start_timer() }
        binding.timerTexViewTwo.setText("0:$seekBarStartingProgress")
        mediaPlayer = MediaPlayer.create(applicationContext, R.raw.alarm)
        binding.volumeSeekBar.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                update(progress)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }

        })
    }

    private fun update(progress: Int){
        val minutes = progress / 60
        val seconds = progress % 60
        var secondsFinal = ""

        if(seconds <= 9){
            secondsFinal = secondsFinal.plus("0$seconds")
        }
        else{
            secondsFinal = secondsFinal.plus(seconds)
        }
        binding.volumeSeekBar.setProgress(progress)
        binding.timerTexViewTwo.setText("$minutes:$secondsFinal")
    }

    private fun start_timer() {
        if(!counterIsActive){
            val seekBarProgressInt = binding.volumeSeekBar.getProgress()
            counterIsActive = true;

            binding.volumeSeekBar.setEnabled(false)
            binding.startStopButtonTwo.setText("STOP")
            countDownTimer = object:CountDownTimer((seekBarProgressInt * 1000).toLong(), 1000){
                override fun onTick(millisUntilFinished: Long) {
                    update((millisUntilFinished / 1000).toInt())
                }

                override fun onFinish() {
                    reset()
                    if(mediaPlayer != null){
                        mediaPlayer?.start()
                    }
                }
            }.start()
        }
        else{
            reset()
        }
    }

    private fun reset() {
        binding.timerTexViewTwo.setText("Press START!")
        binding.startStopButtonTwo.setText("START")
        binding.volumeSeekBar.setProgress(seekBarStartingProgress)
        binding.volumeSeekBar.setEnabled(true)
        countDownTimer?.cancel()
        counterIsActive = false
    }

    override fun onPause() {
        super.onPause()
        if(counterIsActive){
            countDownTimer?.cancel()
            mediaPlayer?.release()

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if(counterIsActive){
            countDownTimer?.cancel()
            mediaPlayer?.release()

        }
    }
}