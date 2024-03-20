package com.mfo.mercadolibreclone.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mfo.mercadolibreclone.databinding.ActivityFavoritesBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoritesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFavoritesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityFavoritesBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initListeners()
    }

    private fun initListeners() {
        binding.btnBack.setOnClickListener() {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
        binding.btnSearch.setOnClickListener() {
            val intent = Intent(this, SearchActivity::class.java)
            startActivity(intent)
            finish()
        }
        binding.btnCart.setOnClickListener() {
            val intent = Intent(this, CartActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}