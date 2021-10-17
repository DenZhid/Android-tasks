package ru.spbstu.icc.kspt.lab2.continuewatch

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    var secondsElapsed: Int = 0
    private lateinit var textSecondsElapsed: TextView

    @Volatile
    var stopped = false

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

    override fun onStart() {
        super.onStart()
        stopped = false
    }

    override fun onStop() {
        super.onStop()
        stopped = true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        secondsElapsed = savedInstanceState?.getInt(SECONDS) ?: 0
        setContentView(R.layout.activity_main)
        textSecondsElapsed = findViewById(R.id.textSecondsElapsed)
        backgroundThread.start()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt(SECONDS, secondsElapsed)
        super.onSaveInstanceState(outState)
    }
}
