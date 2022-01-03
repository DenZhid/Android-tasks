package com.denzhid.imageviaexecutorservice

import android.app.Application
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import java.net.URL
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.Future

class MainActivity : AppCompatActivity() {

    class ExecutorServiceApplication: Application() {
        val executorService: ExecutorService = Executors.newSingleThreadExecutor()
    }

    private val url = URL(
        "https://cdn.fishki.net/upload/post/2021/02/23/3626593/2022180-original.png"
    )
    private lateinit var mIcon: Bitmap
    private lateinit var imageView: ImageView
    private lateinit var future: Future<*>
    private val executor by lazy { (application as ExecutorServiceApplication).executorService }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i("Application", "Application has started")
        Log.i("Executor", "Executor has started")
        setContentView(R.layout.activity_main)
        imageView = findViewById(R.id.image)
        future = executor.submit {
            mIcon = BitmapFactory.decodeStream(url.openConnection().getInputStream())
            runOnUiThread {
                imageView.setImageBitmap(mIcon)
                Log.i("Image", "Image has been posted")
            }
            Log.i("Executor", "Executor has finished task")
        }
        Log.i("Executor", "Executor has submitted task")
        Log.i("Application", "The activity is visible again")
    }

    override fun onStop() {
        Log.i("Application", "The activity is no longer visible")
        super.onStop()
    }

    override fun onDestroy() {
        executor.shutdownNow()
        Log.i("Executor", "Executor has shut down")
        super.onDestroy()
    }
}