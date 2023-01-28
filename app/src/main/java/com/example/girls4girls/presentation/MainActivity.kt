package com.example.girls4girls.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.girls4girls.R
import com.example.girls4girls.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navController = findNavController(R.id.fragmentContainer)
        binding.bottomNav.setupWithNavController(navController)

        val bottomNavFragments = listOf(R.id.homeFragment,
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
}