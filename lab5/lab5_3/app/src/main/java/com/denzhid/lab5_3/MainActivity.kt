package com.denzhid.lab5_3

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import kotlinx.coroutines.*
import java.net.URL

class MainActivity : AppCompatActivity() {
    private val url = URL(
            "https://cdn.fishki.net/upload/post/2021/02/23/3626593/2022180-original.png"
    )
    private lateinit var mIcon: Bitmap
    private lateinit var imageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i(
            "Application_state",
            "Application has started"
        )
        setContentView(R.layout.activity_main)
        imageView = findViewById(R.id.image)
        GlobalScope.launch {
            Log.i("Coroutine", "Coroutine started")
            mIcon = BitmapFactory.decodeStream(url.openConnection().getInputStream())
            runOnUiThread {
                imageView.setImageBitmap(mIcon)
                Log.i("Image", "Image posted")
            }
            Log.i("Coroutine", "Coroutine finished task")
        }
    }

    override fun onStop() {
        Log.i("Application_state", "The activity is no longer visible")
        super.onStop()
    }
}