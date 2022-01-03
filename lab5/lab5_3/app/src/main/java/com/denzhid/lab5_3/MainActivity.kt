package com.denzhid.lab5_3

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.lifecycle.lifecycleScope
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
        Log.i("Application", "Application has started")
        setContentView(R.layout.activity_main)
        imageView = findViewById(R.id.image)
        lifecycleScope.launch {
            Log.i("Coroutine", "Coroutine has started")
            downloadImage()
            imageView.setImageBitmap(mIcon)
            Log.i("Image", "Image has been posted")
            Log.i("Coroutine", "Coroutine has finished task")
        }
    }

    private suspend fun downloadImage() = withContext(Dispatchers.IO) {
        mIcon = BitmapFactory.decodeStream(url.openConnection().getInputStream())

    }

    override fun onStop() {
        Log.i("Application", "Activity is no longer visible")
        super.onStop()
    }
}