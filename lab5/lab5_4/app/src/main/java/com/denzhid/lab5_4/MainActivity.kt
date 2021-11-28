package com.denzhid.lab5_4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.squareup.picasso.Picasso

class MainActivity : AppCompatActivity() {
    private val url = "https://cdn.fishki.net/upload/post/2021/02/23/3626593/2022180-original.png"
    private lateinit var imageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        imageView = findViewById(R.id.image)
        Picasso.get().load(url).into(imageView)
    }
}