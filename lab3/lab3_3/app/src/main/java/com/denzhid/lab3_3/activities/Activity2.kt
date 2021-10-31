package com.denzhid.lab3_3.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import com.denzhid.lab3_3.R
import com.denzhid.lab3_3.databinding.Activity2Binding

class Activity2: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = Activity2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.toFirstButton.setOnClickListener {
            finish()
        }
        binding.toThirdButton.setOnClickListener {
            val intent = Intent(this, Activity3::class.java)
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