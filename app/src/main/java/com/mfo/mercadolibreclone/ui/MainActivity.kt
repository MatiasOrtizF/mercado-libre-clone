package com.mfo.mercadolibreclone.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView
import com.mfo.mercadolibreclone.R
import com.mfo.mercadolibreclone.databinding.ActivityMainBinding
import com.mfo.mercadolibreclone.ui.login.LoginActivity
import com.mfo.mercadolibreclone.ui.myAccount.MyAccountState
import com.mfo.mercadolibreclone.ui.myAccount.MyAccountViewModel
import com.mfo.mercadolibreclone.utils.PreferenceHelper
import com.mfo.mercadolibreclone.utils.PreferenceHelper.get
import com.mfo.mercadolibreclone.utils.PreferenceHelper.set
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity(){

    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModels()
    private lateinit var toolbar: Toolbar
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationView: NavigationView
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        toolbar = findViewById(R.id.toolbar_main)
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayShowTitleEnabled(false)

        drawerLayout = findViewById(R.id.drawer)
        navigationView = findViewById(R.id.nav_view)

        navController = findNavController(R.id.fragmentContainerView)
        appBarConfiguration = AppBarConfiguration(setOf(R.id.id_home_fragment, R.id.id_search_fragment, R.id.id_shopping_fragment, R.id.id_favorites_fragment, R.id.id_offers_fragment, R.id.id_history_fragment, R.id.id_my_account_fragment, R.id.id_category_fragment))

        toggle = ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.nav_drawer_open, R.string.nav_drawer_close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        //setupActionBarWithNavController(navController, drawerLayout)
        navigationView.setupWithNavController(navController)

        val preferences = PreferenceHelper.defaultPrefs(this)

        initUI()

        val token = preferences.getString("jwt", "").toString()
        mainViewModel.getUser(token)

    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.fragmentContainerView)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    private fun initUI() {
        val navHeaderView = navigationView.getHeaderView(0)
        val btnGoToLogin = navHeaderView.findViewById<Button>(R.id.btnGoToLogin)
        val tvUserName = navHeaderView.findViewById<TextView>(R.id.tvUserName)
        val tvMyProfile = navHeaderView.findViewById<TextView>(R.id.tvMyProfile)

        initUIState(btnGoToLogin, tvUserName, tvMyProfile)
        initListeners(btnGoToLogin)
    }

    private fun initUIState(btnGoToLogin: Button, tvUserName: TextView, tvMyProfile: TextView) {
        lifecycleScope.launch {
            mainViewModel.state.collect {
                when(it) {
                    MainState.Loading -> loadingState()
                    is MainState.Error -> errorState(it.error)
                    is MainState.Success -> successState(it, tvUserName, tvMyProfile, btnGoToLogin)
                }
            }
        }
    }

    private fun loadingState() {
        //binding.pb.isVisible = true
    }

    private fun errorState(error: String) {
        if(error == "invalid token") {
            clearSessionPreferences()
        }
    }

    private fun successState(state: MainState.Success, tvUserName: TextView, tvMyProfile: TextView, btnGoToLogin: Button) {
        tvUserName.text = state.user.name
        tvMyProfile.text = getString(R.string.tv_my_profile)
        btnGoToLogin.isVisible = false
    }

    private fun clearSessionPreferences() {
        val preferences = PreferenceHelper.defaultPrefs(this)
        preferences["jwt"] = ""
    }

    private fun initListeners(btnGoToLogin: Button) {
        btnGoToLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
        binding.include.btnCart.setOnClickListener {
            navController.navigate(R.id.id_cart_fragment)
        }
        binding.include.btnSearch.setOnClickListener {
            navController.navigate(R.id.id_search_fragment)
        }
    }
}