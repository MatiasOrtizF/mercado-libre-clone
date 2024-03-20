package com.mfo.mercadolibreclone.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mfo.mercadolibreclone.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OffersActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_offers)
    }
}