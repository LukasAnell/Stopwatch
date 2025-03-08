package com.example.stopwatch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.stopwatch.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    var isStopped = true
    var isReset = true
    var displayTime = 0L
    // var lapsCount = 0

    // public static final double PI = 3.14;    declaring a classwide constant in java
    // in kotlin, we use a companion object
    companion object {
        // TAG is the default var name for labeling your log statements
        val TAG = "MainActivity"

        // just for github testing purposes
        val ASTROPHYSICISTS_PI = 3

        // make constants for your key-value pairs
        val STATE_DISPLAY_TIME = "the display time"
        val STATE_IS_RUNNING = "is the clock running?"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.layout_main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // laps.text = "Laps: $lapsCount"
        binding.buttonMainStartStop.text = "Start"
        binding.buttonMainReset.text = "Reset"
        binding.chronometerMainStopwatch.base = SystemClock.elapsedRealtime()
        Log.d(TAG, "onCreate: this is a log")

        // restore saveInstanceState if it exists
        if(savedInstanceState != null) {
            displayTime = savedInstanceState.getLong(STATE_DISPLAY_TIME)
            // solve for base:
            // base = elapsedTime - displayTime
            binding.chronometerMainStopwatch.base = SystemClock.elapsedRealtime() - displayTime
            Log.d(TAG, "$displayTime")
            Log.d(TAG, "${binding.chronometerMainStopwatch.base}")

            isStopped = !savedInstanceState.getBoolean(STATE_IS_RUNNING)
            if(!isStopped) {
                binding.chronometerMainStopwatch.start()
                binding.buttonMainStartStop.text = "Stop"
            } else {
                binding.chronometerMainStopwatch.stop()
                binding.buttonMainStartStop.text = "Start"
            }
        }

        binding.buttonMainStartStop.setOnClickListener {
            if(isStopped) {
                isStopped = false
                // isReset = true
                binding.buttonMainStartStop.text = "Stop"
                // buttonReset.text = "Lap"
                binding.chronometerMainStopwatch.base = SystemClock.elapsedRealtime() - displayTime
                binding.chronometerMainStopwatch.start()
            } else {
                isStopped = true
                // isReset = false
                binding.buttonMainStartStop.text = "Start"
                //buttonReset.text = "Reset"
                binding.chronometerMainStopwatch.stop()
                displayTime = kotlin.math.abs(binding.chronometerMainStopwatch.base - SystemClock.elapsedRealtime())

            }
        }

        binding.buttonMainReset.setOnClickListener {
            if(isReset) {
                binding.buttonMainStartStop.text = "Start"
                isStopped = true
                binding.chronometerMainStopwatch.base = SystemClock.elapsedRealtime()
                displayTime = 0
                binding.chronometerMainStopwatch.stop()
            }
        }
    }

    // to override an existing function, just start typing it
    // can do this for the rest of the lifecycle functions (or any
    // function that exists in the superclass
    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart: ")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "onRestart: ")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume: ")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause: ")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop: ")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy: ")
    }

    // Use this to preserve state through orientation changes
    override fun onSaveInstanceState(outState: Bundle) {
        // Figure out the time that is currently displayed on the screen
        // and save that in a key-value pair in the bundle
        if(!isStopped) {
            displayTime = SystemClock.elapsedRealtime() - binding.chronometerMainStopwatch.base
        }
        // if it weren't running, you would have saved the displayTime
        // in the stop button's onClickListener

        // save key-value pairs to the bundle before the super class call
        outState.putLong(STATE_DISPLAY_TIME, displayTime)
        outState.putBoolean(STATE_IS_RUNNING, !isStopped)
        super.onSaveInstanceState(outState)
    }
}