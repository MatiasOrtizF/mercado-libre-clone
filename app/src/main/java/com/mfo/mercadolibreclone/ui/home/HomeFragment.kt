package com.mfo.mercadolibreclone.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mfo.mercadolibreclone.databinding.FragmentHomeBinding
import com.mfo.mercadolibreclone.utils.PreferenceHelper
import com.mfo.mercadolibreclone.utils.PreferenceHelper.set
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
    }

    private fun initUI() {
        initList()
    }

    private fun initList() {
        binding.btnLogOut.setOnClickListener {
            logOut()
        }
    }

    private fun logOut() {
        clearSessionPreferences()
    }

    private fun clearSessionPreferences() {
        val preferences = PreferenceHelper.defaultPrefs(requireContext())
        preferences["jwt"] = ""
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }
}