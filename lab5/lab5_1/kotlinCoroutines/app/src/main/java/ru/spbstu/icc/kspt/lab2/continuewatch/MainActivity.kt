package ru.spbstu.icc.kspt.lab2.continuewatch

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private lateinit var textSecondsElapsed: TextView
    private lateinit var sharedPref: SharedPreferences

    @Volatile
    private var isOnScreen = false

    @Volatile
    private var startingTime: Long = 0

    @Volatile
    private var secondsElapsedWhenThreadStarted: Long = 0

    companion object {
        const val SECONDS = "seconds"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i("Application", "Application has started")
        setContentView(R.layout.activity_main)
        textSecondsElapsed = findViewById(R.id.textSecondsElapsed)
        sharedPref = getSharedPreferences(SECONDS, Context.MODE_PRIVATE)
        sharedPref.edit().clear().apply()
        lifecycleScope.launchWhenResumed() {
            while (isOnScreen) {
                wait()
                textSecondsElapsed.text =
                    String.format(getString(R.string.current_seconds), getCurrentTimeElapsed())
            }
        }
    }

    private suspend fun wait() = withContext(Dispatchers.Default) {
        delay(Random.nextLong(2000))
    }

    override fun onStart() {
        secondsElapsedWhenThreadStarted = sharedPref
            .getLong(SECONDS, secondsElapsedWhenThreadStarted)
        isOnScreen = true
        startingTime = System.currentTimeMillis()
        Log.i("Application", "Activity is visible again")
        super.onStart()
    }

    override fun onResume() {
        Log.i("Coroutine", "Coroutine is running again")
        super.onResume()

    }

    override fun onPause() {
        isOnScreen = false
        Log.i("Coroutine", "Coroutine has stopped")
        super.onPause()
    }

    override fun onStop() {
        with(sharedPref.edit()) {
            putLong(SECONDS, getCurrentTimeElapsed())
            apply()
        }
        Log.i("Application", "Activity is no longer visible")
        super.onStop()
    }

    override fun onDestroy() {
        Log.i("Coroutine", "Coroutine has been destroyed")
        super.onDestroy()
    }

    private fun getCurrentTimeElapsed(): Long {
        return secondsElapsedWhenThreadStarted +
                ((System.currentTimeMillis() - startingTime) / 1000)
    }
}
