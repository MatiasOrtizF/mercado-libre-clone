package com.mfo.mercadolibreclone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mfo.mercadolibreclone.databinding.ActivityCartBinding
import com.mfo.mercadolibreclone.databinding.ActivityMainBinding

class CartActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCartBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCartBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initListeners()
    }

    private fun initListeners() {
        binding.btnDiscoverProducts.setOnClickListener() {
            val intent = Intent(this, OffersActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}