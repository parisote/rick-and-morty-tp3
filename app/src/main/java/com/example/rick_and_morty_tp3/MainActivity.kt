package com.example.rick_and_morty_tp3

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var navHostFragment: NavHostFragment
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationView: NavigationView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        drawerLayout = findViewById(R.id.drawer_layout_id)
        navigationView = findViewById(R.id.nav_view)
        navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        setupDrawerLayout()
    }

    private fun setupDrawerLayout() {
        val navController = navHostFragment.navController
        navigationView.setupWithNavController(navController)
        NavigationUI.setupActionBarWithNavController(this, navController, drawerLayout)
        navController.addOnDestinationChangedListener { _, destination, _ ->

            supportActionBar?.setHomeAsUpIndicator(R.drawable.hamburguer)
            //segun el destino oculto el actionbar
            if (destination.id == R.id.loginFragment)
                supportActionBar?.hide()
            else{
                supportActionBar?.show()
                when (destination.id) {
                    R.id.homeFragment -> supportActionBar!!.title = getString(R.string.menu_home)
                    R.id.favoritesFragment -> supportActionBar!!.title = getString(R.string.menu_favourites)
                    R.id.settingsFragment -> supportActionBar!!.title = getString(R.string.menu_settings)
                    R.id.characterDetailFragment -> supportActionBar!!.title = getString(R.string.menu_characterDetail)
                }
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            drawerLayout.openDrawer(GravityCompat.START)
        }
        return false
    }
}