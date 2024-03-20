package com.mfo.mercadolibreclone.ui

import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import com.mfo.mercadolibreclone.R
import com.mfo.mercadolibreclone.databinding.ActivityMainBinding
import com.mfo.mercadolibreclone.ui.category.CategoriesActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var binding: ActivityMainBinding

    private lateinit var drawer: DrawerLayout
    private lateinit var toggle: ActionBarDrawerToggle


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val toolbar: Toolbar = binding.include.toolbarMain
        setSupportActionBar(toolbar)

        drawer = binding.drawerLayaout

        toggle = ActionBarDrawerToggle(this, drawer, toolbar,
            R.string.nav_drawer_open,
            R.string.nav_drawer_close
        )
        drawer.addDrawerListener(toggle)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        val navigationView: NavigationView = binding.navView
        navigationView.setNavigationItemSelectedListener(this)

        initListeners()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.nav_item_home -> goToHome()
            R.id.nav_item_search -> goToSearch()
            R.id.nav_item_my_shopping -> goToMyShopping()
            R.id.nav_item_favorites -> goToFavorites()
            R.id.nav_item_offers -> goToOffers()
            R.id.nav_item_history -> goToHistory()
            R.id.nav_item_my_count -> goToMyAccount()
            R.id.nav_item_categories -> goToCategories()
        }

        drawer.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        toggle.syncState()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        toggle.onConfigurationChanged(newConfig)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun initListeners() {
        binding.include.btnCart.setOnClickListener() {
            val intent = Intent(this, CartActivity::class.java)
            startActivity(intent)
            finish()
        }
        binding.include.btnSearch.setOnClickListener() {
            val intent = Intent(this, SearchActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun goToHome() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun goToSearch() {
        val intent = Intent(this, SearchActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun goToMyShopping() {
        /*val intent = Intent(this, CartActivity::class.java)
        startActivity(intent)
        finish()
       */
        println("Go to my shopping")
    }

    private fun goToFavorites() {
        val intent = Intent(this, FavoritesActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun goToOffers() {
        val intent = Intent(this, OffersActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun goToHistory() {
        /*val intent = Intent(this, CartActivity::class.java)
        startActivity(intent)
        finish()
        */
        println("Go to history")
    }

    private fun goToMyAccount() {
        val intent = Intent(this, MyAccountActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun goToCategories() {
        val intent = Intent(this, CategoriesActivity::class.java)
        startActivity(intent)
        finish()
    }
}