package com.denzhid.lab3_4.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import com.denzhid.lab3_4.R
import com.denzhid.lab3_4.databinding.Activity3Binding

class Activity3: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = Activity3Binding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.toSecondButton.setOnClickListener {
            val intent = Intent(this, Activity2::class.java)
                .addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
            startActivity(intent)
        }
        binding.navView.setNavigationItemSelectedListener {
            if (it.itemId == R.id.activity_about) {
                binding.drawerLayout.closeDrawer(GravityCompat.START, true)
                startActivity(Intent(this, ActivityAbout::class.java))
            }
            true
        }
    }
}