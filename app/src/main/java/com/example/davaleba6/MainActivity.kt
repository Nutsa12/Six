package com.example.davaleba6

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        RestClient.initClient()

        bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavView)
        navController = findNavController(R.id.navHostFragment)

        setupActionBarWithNavController(navController, AppBarConfiguration(
            setOf(
                R.layout.fragment_home,
                R.layout.fragment_network,
                R.layout.fragment_dashboard,
                R.layout.fragment_notification
            )
        ))

        bottomNavigationView.setupWithNavController(navController)
    }
}