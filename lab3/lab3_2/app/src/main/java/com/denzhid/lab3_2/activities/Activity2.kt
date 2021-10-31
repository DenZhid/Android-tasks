package com.denzhid.lab3_2.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import com.denzhid.lab3_2.R
import com.denzhid.lab3_2.databinding.Activity2Binding

class Activity2: AppCompatActivity() {

    private val WHERE_TO_GO = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = Activity2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.toFirstButton.setOnClickListener {
            finish()
        }
        binding.toThirdButton.setOnClickListener {
            val intent = Intent(this, Activity3::class.java)
            startActivityForResult(intent, WHERE_TO_GO)
        }
        binding.navView.setNavigationItemSelectedListener {
            if (it.itemId == R.id.activity_about) {
                binding.drawerLayout.closeDrawer(GravityCompat.START, true)
                startActivity(Intent(this, ActivityAbout::class.java))
            }
            true
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == WHERE_TO_GO && resultCode == 1) {
            finish()
        }
    }
}