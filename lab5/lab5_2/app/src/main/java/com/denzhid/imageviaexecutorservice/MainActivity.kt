package com.denzhid.imageviaexecutorservice

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import java.net.URL
import java.util.concurrent.Executors

class MainActivity : AppCompatActivity() {
    private val url = URL(
        "https://cdn.fishki.net/upload/post/2021/02/23/3626593/2022180-original.png"
    )
    private lateinit var mIcon: Bitmap
    private lateinit var imageView: ImageView
    private val executorService = Executors.newSingleThreadExecutor()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i(
            "Application_state",
            "Applications has started"
        )
        setContentView(R.layout.activity_main)
        imageView = findViewById(R.id.image)
        executorService.execute {
            Log.i("Executor", "Executor started")
            mIcon = BitmapFactory.decodeStream(url.openConnection().getInputStream())
            runOnUiThread {
                imageView.setImageBitmap(mIcon)
                Log.i("Image", "Image posted")
            }
            Log.i("Executor", "Executor finished task")
        }
    }

    override fun onStop() {
        Log.i("Application_state", "The activity is no longer visible")
        super.onStop()
    }

    override fun onDestroy() {
        executorService.shutdownNow()
        super.onDestroy()
    }
}