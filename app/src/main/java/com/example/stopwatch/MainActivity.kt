package com.example.stopwatch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Chronometer
import androidx.constraintlayout.widget.ConstraintLayout
import kotlin.math.log

class MainActivity : AppCompatActivity() {
    lateinit var layoutMain: ConstraintLayout
    lateinit var buttonStartStop: Button
    lateinit var buttonReset: Button
    lateinit var chronometer: Chronometer

    // public static final double PI = 3.14     declaring a classwide constant in java
    // in kotlin, we use a companion object
    companion object {
        // TAG is the default var name for labeling your log statements
        val TAG = "MainActivity"

        // just for github testing purposes
        val ASTROPHYSICISTS_PI = 3
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d(TAG, "onCreate: this is a log")
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

    fun wireWidgets() {
        layoutMain = findViewById(R.id.layout_main)
        buttonStartStop = findViewById(R.id.button_main_startStop)
        buttonReset = findViewById(R.id.button_main_reset)
    }
}