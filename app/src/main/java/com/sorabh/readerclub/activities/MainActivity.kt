package com.sorabh.readerclub.activities

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.FrameLayout
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import com.sorabh.readerclub.R
import com.sorabh.readerclub.fragments.DashboardFragment
import com.sorabh.readerclub.fragments.FavoriteFragment

class MainActivity : AppCompatActivity() {
    lateinit var drawerLayout: DrawerLayout
    lateinit var navigationView: NavigationView
    lateinit var toolbar: Toolbar
    lateinit var frameLayout: FrameLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initializeViews()
        setUpActionbar(toolbar)
        val actionBarDrawerToggle = ActionBarDrawerToggle(
            this,
            drawerLayout,
            R.string.action_close,R.string.action_open
        )
        drawerLayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()

            supportFragmentManager
                .beginTransaction()
                .replace(R.id.frameLayout,DashboardFragment())
                .addToBackStack("drawerDashboard")
                .commit()
            supportActionBar?.title = "Book List"
            navigationView.setCheckedItem(R.id.drawerDashboard)


        navigationView.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.drawerDashboard -> {
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.frameLayout,DashboardFragment())
                        .addToBackStack("drawerDashboard")
                        .commit()
                    supportActionBar?.title = "Book List"
                    navigationView.setCheckedItem(R.id.drawerDashboard)
                    drawerLayout.closeDrawer(GravityCompat.START)
                }
                R.id.drawerFavorite -> {
                    supportFragmentManager
                        .beginTransaction()
                        .addToBackStack("drawerFavorite")
                        .replace(R.id.frameLayout,FavoriteFragment())
                        .commit()
                        supportActionBar?.title = "Favorite Books"
                    navigationView.setCheckedItem(R.id.drawerFavorite)
                    drawerLayout.closeDrawer(GravityCompat.START)
                }
                R.id.drawerShare ->{
                    val intent = Intent(Intent.ACTION_SEND)
                    startActivity(Intent.createChooser(intent,"Share Using : "))
                }
            }
            true
        }
    }

    private fun initializeViews() {
        drawerLayout = findViewById(R.id.drawerLayout)
        navigationView = findViewById(R.id.navigationView)
        toolbar = findViewById(R.id.toolBar)
        frameLayout = findViewById(R.id.frameLayout)
    }

    private fun setUpActionbar(toolbar: Toolbar?){
        setSupportActionBar(toolbar)
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.title= "Home"
        actionBar?.hide()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == android.R.id.home){
            drawerLayout.openDrawer(GravityCompat.START)
        }
        return super.onOptionsItemSelected(item)
    }
}