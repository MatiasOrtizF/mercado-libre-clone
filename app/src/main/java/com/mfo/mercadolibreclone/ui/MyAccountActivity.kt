package com.mfo.mercadolibreclone.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mfo.mercadolibreclone.databinding.ActivityMyAccountBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MyAccountActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMyAccountBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyAccountBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initListeners()
    }

    private fun initListeners() {
        binding.btnBack.setOnClickListener() {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}