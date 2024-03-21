package com.mfo.mercadolibreclone.ui.myShopping

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mfo.mercadolibreclone.R
import com.mfo.mercadolibreclone.databinding.FragmentShoppingBinding

class ShoppingFragment : Fragment() {
    private var _binding: FragmentShoppingBinding? = null
    private val binding = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        FragmentShoppingBinding.inflate(inflater, container, false)
        return binding.root
    }
}