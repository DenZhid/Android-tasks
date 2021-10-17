package ru.spbstu.icc.kspt.lab2.continuewatch

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    var secondsElapsed: Int = 0
    lateinit var textSecondsElapsed: TextView
    var stopped = false
    private lateinit var sharedPref: SharedPreferences

    companion object {
        const val SECONDS = "seconds"
    }

    private var backgroundThread = Thread {
        while (true) {
            if (!stopped) {
                Thread.sleep(1000)
                textSecondsElapsed.post {
                    textSecondsElapsed.text =
                        String.format(getString(R.string.current_seconds), secondsElapsed++)
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textSecondsElapsed = findViewById(R.id.textSecondsElapsed)
        sharedPref = getSharedPreferences(SECONDS, Context.MODE_PRIVATE)
        backgroundThread.start()
    }

    override fun onStop() {
        stopped = true
        with(sharedPref.edit()) {
            putInt(SECONDS, secondsElapsed)
            apply()
        }
        super.onStop()
    }

    override fun onStart() {
        stopped = false
        secondsElapsed = sharedPref.getInt(SECONDS, secondsElapsed)
        super.onStart()
    }
}
