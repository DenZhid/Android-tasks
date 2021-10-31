package com.denzhid.lab3_4.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.denzhid.lab3_4.databinding.ActivityAboutBinding

class ActivityAbout: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityAboutBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}