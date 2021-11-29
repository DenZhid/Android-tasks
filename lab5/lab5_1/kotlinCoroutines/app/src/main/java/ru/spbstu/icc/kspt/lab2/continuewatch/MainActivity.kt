package ru.spbstu.icc.kspt.lab2.continuewatch

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private lateinit var textSecondsElapsed: TextView
    private lateinit var sharedPref: SharedPreferences
    private lateinit var coroutine: Job
    @Volatile
    private var startingTime: Long = 0
    @Volatile
    private var secondsElapsedWhenThreadStarted: Long = 0

    companion object {
        const val SECONDS = "seconds"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i(
            "Application_state",
            "Applications has started"
        )
        setContentView(R.layout.activity_main)
        textSecondsElapsed = findViewById(R.id.textSecondsElapsed)
        sharedPref = getSharedPreferences(SECONDS, Context.MODE_PRIVATE)
        coroutine = lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                while (true) {
                    delay(Random.nextLong(2000))
                    textSecondsElapsed.post {
                        textSecondsElapsed.text =
                            String.format(
                                getString(R.string.current_seconds),
                                getCurrentTimeElapsed()
                            )
                    }
                }
            }
        }
        Log.i("Coroutine", "Coroutine has started")
    }

    override fun onStart() {
        secondsElapsedWhenThreadStarted = sharedPref
            .getLong(SECONDS, secondsElapsedWhenThreadStarted)
        startingTime = System.currentTimeMillis()
        Log.i("Application_state", "The activity is visible again")
        Log.i("Coroutine", "Coroutine is running again")
        super.onStart()
    }

    override fun onPause() {
        Log.i("Coroutine", "Coroutine has stopped")
        super.onPause()
    }

    override fun onStop() {
        with(sharedPref.edit()) {
            putLong(SECONDS, getCurrentTimeElapsed())
            apply()
        }
        Log.i("Application_state", "The activity is no longer visible")
        super.onStop()
    }

    private fun getCurrentTimeElapsed(): Long {
        return secondsElapsedWhenThreadStarted +
                ((System.currentTimeMillis() - startingTime) / 1000)
    }
}