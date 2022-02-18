package com.denzhid.testimplicitintentsapp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

class SomeActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i("Activity", "Activity was started!")
    }
}