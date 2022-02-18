package com.denzhid.testimplicitintentsapp

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<Button>(R.id.callIntent).setOnClickListener {
            val i = Intent().apply {
                action = Intent.ACTION_VIEW
                data = Uri.parse("https://rutube.ru/video1")
            }
            startActivity(i)
        }
    }
}