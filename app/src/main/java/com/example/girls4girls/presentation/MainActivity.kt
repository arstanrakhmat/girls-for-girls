package com.example.girls4girls.presentation

import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.findNavController
import androidx.navigation.ui.onNavDestinationSelected
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.girls4girls.R
import com.example.girls4girls.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var bottomNavFragments: List<Int>
    private lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bottomNavigationView = binding.bottomNavigationView
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainer) as NavHostFragment
        navController = navHostFragment.navController
        bottomNavigationView.setupWithNavController(navController)

        //Toolbar
        toolbar = binding.appbar
        setSupportActionBar(toolbar)
        setupActionBarWithNavController(navController)

        bottomNavFragments = listOf(
            R.id.homeFragment,
            R.id.mentorshipFragment,
            R.id.trainingsListFragment,
            R.id.videoblogsListFragment
        )

        navController.addOnDestinationChangedListener { _, nd: NavDestination, _ ->
            if (nd.id in bottomNavFragments) {
                binding.bottomNavigationView.visibility = View.VISIBLE
                supportActionBar?.setDisplayHomeAsUpEnabled(false)
            } else {
                binding.bottomNavigationView.visibility = View.GONE
                supportActionBar?.setDisplayHomeAsUpEnabled(true)
            }
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return item.onNavDestinationSelected(navController) || super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        if (resources?.configuration?.orientation == LANDSCAPE_SCREEN_ID) {
            requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        } else {
            super.onBackPressed()
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.fragmentContainer)
        return navController.navigateUp()
    }

    companion object {
        const val LANDSCAPE_SCREEN_ID = 2
        const val PORTRAIT_SCREEN_ID = 1
    }

}