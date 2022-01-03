package ru.spbstu.icc.kspt.lab2.continuewatch

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.Future
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    class ExecutorServiceApplication: Application() {
        var executorService: ExecutorService  = Executors.newSingleThreadExecutor()
    }

    private lateinit var textSecondsElapsed: TextView
    private lateinit var sharedPref: SharedPreferences
    private val executor by lazy { (application as ExecutorServiceApplication).executorService }
    private lateinit var future: Future<*>

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
        Log.i("Executor", "Executor has started")
        setContentView(R.layout.activity_main)
        textSecondsElapsed = findViewById(R.id.textSecondsElapsed)
        sharedPref = getSharedPreferences(SECONDS, Context.MODE_PRIVATE)
        sharedPref.edit().clear().apply()
    }

    override fun onStart() {
        secondsElapsedWhenThreadStarted = sharedPref
            .getLong(SECONDS, secondsElapsedWhenThreadStarted)
        startingTime = System.currentTimeMillis()
        future = executor.submit {
            while (!Thread.currentThread().isInterrupted) {
                try {
                    Thread.sleep(Random.nextLong(2000))
                    textSecondsElapsed.post {
                        textSecondsElapsed.text =
                            String.format(
                                getString(R.string.current_seconds), getCurrentTimeElapsed())
                    }
                } catch (e: InterruptedException) {
                    Thread.currentThread().interrupt()
                    Log.i("Thread", "Thread has stopped")
                }
            }
        }
        Log.i("Executor", "Executor has submitted task")
        Log.i("Application", "Activity is visible again")
        super.onStart()
    }

    override fun onStop() {
        future.cancel(true)
        Log.i("Executor", "Executor has canceled task")
        with(sharedPref.edit()) {
            putLong(SECONDS, getCurrentTimeElapsed())
            apply()
        }
        Log.i("Application", "Activity is no longer visible")
        super.onStop()
    }

    override fun onDestroy() {
        executor.shutdownNow()
        Log.i("Executor", "Executor has shut down")
        super.onDestroy()
    }

    private fun getCurrentTimeElapsed(): Long {
        return secondsElapsedWhenThreadStarted +
                ((System.currentTimeMillis() - startingTime) / 1000)
    }
}
