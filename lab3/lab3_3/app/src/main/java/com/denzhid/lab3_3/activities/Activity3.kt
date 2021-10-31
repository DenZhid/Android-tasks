package com.denzhid.lab3_3.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import com.denzhid.lab3_3.R
import com.denzhid.lab3_3.databinding.Activity3Binding

class Activity3: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = Activity3Binding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.toFirstButton.setOnClickListener {
            val intent = Intent(this, Activity1::class.java)
                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
        }
        binding.toSecondButton.setOnClickListener {
            finish()
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