package com.example.stopwatch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import android.widget.Button
import android.widget.Chronometer
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout

class MainActivity : AppCompatActivity() {
    lateinit var layoutMain: ConstraintLayout
    lateinit var buttonStartStop: Button
    lateinit var buttonReset: Button
    lateinit var chronometer: Chronometer
    lateinit var laps: TextView

    var isStopped = true
    var isReset = true
    var displayTime = 0L
    // var lapsCount = 0

    // public static final double PI = 3.14     declaring a classwide constant in java
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
        setContentView(R.layout.activity_main)
        wireWidgets()

        // laps.text = "Laps: $lapsCount"
        laps.text = ""
        buttonStartStop.text = "Start"
        buttonReset.text = "Reset"
        chronometer.base = SystemClock.elapsedRealtime()
        Log.d(TAG, "onCreate: this is a log")

        // restore saveInstanceState if it exists
        if(savedInstanceState != null) {
            displayTime = savedInstanceState.getLong(STATE_DISPLAY_TIME)
            // solve for base:
            // base = elapsedTime - displayTime
            chronometer.base = SystemClock.elapsedRealtime() - displayTime
            Log.d(TAG, "$displayTime")
            Log.d(TAG, "${chronometer.base}")

            isStopped = !savedInstanceState.getBoolean(STATE_IS_RUNNING)
            if(!isStopped) {
                chronometer.start()
                buttonStartStop.text = "Stop"
            } else {
                chronometer.stop()
                buttonStartStop.text = "Start"
            }
        }

        buttonStartStop.setOnClickListener {
            if(isStopped) {
                isStopped = false
                // isReset = true
                buttonStartStop.text = "Stop"
                // buttonReset.text = "Lap"
                chronometer.base = SystemClock.elapsedRealtime() + displayTime
                chronometer.start()
            } else {
                isStopped = true
                // isReset = false
                buttonStartStop.text = "Start"
                //buttonReset.text = "Reset"
                chronometer.stop()
                displayTime = chronometer.base - SystemClock.elapsedRealtime()

            }
        }

        buttonReset.setOnClickListener {
            if(isReset) {
                buttonStartStop.text = "Start"
                isStopped = true
                chronometer.base = SystemClock.elapsedRealtime()
                displayTime = 0
                chronometer.stop()
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

    private fun wireWidgets() {
        layoutMain = findViewById(R.id.layout_main)
        buttonStartStop = findViewById(R.id.button_main_startStop)
        buttonReset = findViewById(R.id.button_main_reset)
        chronometer = findViewById(R.id.chronometer_main_stopwatch)
        laps = findViewById(R.id.textView_main_laps)
    }

    // Use this to preserve state through orientation changes
    override fun onSaveInstanceState(outState: Bundle) {
        // Figure out the time that is currently displayed on the screen
        // and save that in a key-value pair in the bundle
        if(!isStopped) {
            displayTime = SystemClock.elapsedRealtime() - chronometer.base
        }
        // if it weren't running, you would have saved the displayTime
        // in the stop button's onClickListener

        // save key-value pairs to the bundle before the super class call
        outState.putLong(STATE_DISPLAY_TIME, displayTime)
        outState.putBoolean(STATE_IS_RUNNING, !isStopped)
        super.onSaveInstanceState(outState)
    }
}