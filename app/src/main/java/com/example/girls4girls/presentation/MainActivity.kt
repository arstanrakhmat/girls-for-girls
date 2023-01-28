package com.example.girls4girls.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.example.girls4girls.R
import com.example.girls4girls.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var bottomNavFragments: List<Int>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navController = findNavController(R.id.fragmentContainer)
        binding.bottomNav.setupWithNavController(navController)
        NavigationUI.setupActionBarWithNavController(this, navController)

        bottomNavFragments = listOf(R.id.homeFragment,
                                        R.id.mentorshipFragment,
                                        R.id.trainingsListFragment,
                                        R.id.videoblogsListFragment,
                                        R.id.forumsListFragment)

        navController.addOnDestinationChangedListener { _, nd: NavDestination, _ ->
            if (nd.id in bottomNavFragments){
                binding.bottomNav.visibility = View.VISIBLE
            } else {
                binding.bottomNav.visibility = View.GONE
            }
        }


    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.fragmentContainer)
        val currentFragment = navController.currentDestination
        return if (currentFragment != null && bottomNavFragments.contains(currentFragment.id)){
            actionBar?.setDisplayHomeAsUpEnabled(false)
            super.onSupportNavigateUp()
        } else {
            navController.navigateUp()
        }
    }

}