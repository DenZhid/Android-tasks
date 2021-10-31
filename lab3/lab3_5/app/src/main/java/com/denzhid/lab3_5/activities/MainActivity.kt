package com.denzhid.lab3_5.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.denzhid.lab3_5.R
import com.denzhid.lab3_5.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView

class MainActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val navController = navHostFragment.navController
        findViewById<NavigationView>(R.id.drawer_nav)
            .setupWithNavController(navController)

        val appBarConfiguration = AppBarConfiguration(navController.graph, binding.drawer)

        setupActionBarWithNavController(
            navController,
            appBarConfiguration
        )

        binding.drawerNav.setNavigationItemSelectedListener {
            if (it.itemId == R.id.activity_about) {
                binding.drawer.closeDrawer(GravityCompat.START, true)
                navController.navigate(R.id.to_activity_about)
            }
            true
        }
    }
}