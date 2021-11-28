package ru.spbstu.icc.kspt.lab2.continuewatch

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private lateinit var textSecondsElapsed: TextView
    private lateinit var sharedPref: SharedPreferences
    private lateinit var executor : ExecutorService
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
    }

    override fun onStart() {
        secondsElapsedWhenThreadStarted = sharedPref
            .getLong(SECONDS, secondsElapsedWhenThreadStarted)
        startingTime = System.currentTimeMillis()
        executor = Executors.newSingleThreadExecutor()
        executor.execute {
            while (!executor.isShutdown) {
                try {
                    Thread.sleep(Random.nextLong(2000))
                    textSecondsElapsed.post {
                        textSecondsElapsed.text =
                            String.format(
                                getString(R.string.current_seconds),
                                getCurrentTimeElapsed()
                            )
                    }
                } catch (e: InterruptedException) {
                    Thread.currentThread().interrupt()
                    Log.i("Executor", "Executor stopped")
                }
            }
        }
        Log.i("Executor", "Executor started")
        Log.i("Application_state", "The activity is visible again")
        super.onStart()
    }

    override fun onStop() {
        executor.shutdownNow()
        Log.i("Executor", "Executor shut down")
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
