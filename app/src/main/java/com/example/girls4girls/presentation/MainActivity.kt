package com.example.girls4girls.presentation

import android.content.pm.ActivityInfo
import android.media.VolumeShaper
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.TypedValue
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

//        binding.bottomNav.layoutParams.height = toPixels(R.dimen.bottom_nav_height)

        bottomNavFragments = listOf(R.id.homeFragment,
                                        R.id.mentorshipFragment,
                                        R.id.trainingsListFragment,
                                        R.id.videoblogsListFragment,
                                        R.id.forumsListFragment)

        navController.addOnDestinationChangedListener { _, nd: NavDestination, _ ->
            if (nd.id in bottomNavFragments){
                binding.bottomNav.visibility = View.VISIBLE
                supportActionBar?.setDisplayHomeAsUpEnabled(false)
            } else {
                binding.bottomNav.visibility = View.GONE
                supportActionBar?.setDisplayHomeAsUpEnabled(true)
            }
        }


    }

    override fun onBackPressed(){
        if (resources?.configuration?.orientation == LANDSCAPE_SCREEN_ID){
            requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
        } else {
            super.onBackPressed()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.fragmentContainer)
        val currentFragment = navController.currentDestination
        return navController.navigateUp()
    }

    companion object {
        val LANDSCAPE_SCREEN_ID = 2
        val PORTRAIT_SCREEN_ID = 1
    }

}