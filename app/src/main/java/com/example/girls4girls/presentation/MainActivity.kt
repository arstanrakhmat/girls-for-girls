package com.example.girls4girls.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.girls4girls.R
import com.example.girls4girls.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bottomNavigationView = binding.bottomNavigationView
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainer) as NavHostFragment
        navController = navHostFragment.navController
        bottomNavigationView.setupWithNavController(navController)

//        val navController = findNavController(R.id.fragmentContainer)
//        binding.bottomNav.setupWithNavController(navController)
//
//        val bottomNavFragments = listOf(R.id.homeFragment,
//                                        R.id.mentorshipFragment,
//                                        R.id.trainingsListFragment,
//                                        R.id.videoblogsListFragment,
//                                        R.id.forumsListFragment)
//
//        navController.addOnDestinationChangedListener { _, nd: NavDestination, _ ->
//            if (nd.id in bottomNavFragments){
//                binding.bottomNav.visibility = View.VISIBLE
//            } else {
//                binding.bottomNav.visibility = View.GONE
//            }
//        }
    }
}