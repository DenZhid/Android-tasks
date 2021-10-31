package com.denzhid.lab3_2.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import com.denzhid.lab3_2.R
import com.denzhid.lab3_2.databinding.Activity3Binding

class Activity3: AppCompatActivity() {

    private val TO_FIRST = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = Activity3Binding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.toFirstButton.setOnClickListener {
            setResult(TO_FIRST)
            finish()
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