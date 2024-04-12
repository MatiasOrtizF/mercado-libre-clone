package com.mfo.mercadolibreclone.ui.myAccount

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.mfo.mercadolibreclone.databinding.FragmentMyAccountBinding
import com.mfo.mercadolibreclone.utils.PreferenceHelper
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MyAccountFragment : Fragment() {
    private var _binding: FragmentMyAccountBinding? = null
    private val binding get() = _binding!!

    private val myAccountViewModel: MyAccountViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val preferences = PreferenceHelper.defaultPrefs(requireContext())
        val token: String = preferences.getString("jwt", "").toString()
        initUI()
        if(token.isNotEmpty()) {
            myAccountViewModel.getUser(token)
        }
    }

    private fun initUI() {
        initUIState()
        initListeners()
    }

    private fun initUIState() {
        lifecycleScope.launch {
            myAccountViewModel.state.collect {
                when(it) {
                    MyAccountState.Loading -> loadingState()
                    is MyAccountState.Error -> errorState(it.error)
                    is MyAccountState.Success -> successState(it)
                }
            }
        }
    }

    private fun loadingState() {
        //binding.pb.isVisible = true
    }

    private fun errorState(error: String) {
        println("el error es:$error")
    }

    private fun successState(state: MyAccountState.Success) {
        //binding.pb.isVisible = false
        binding.tvEnterMLC.isVisible = false
        binding.tvUserName.text = "RAMA6541225"
        binding.tvUserEmail.text = state.user.email
        binding.ivOnOff.isVisible = true
    }

    private fun initListeners() {

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMyAccountBinding.inflate(layoutInflater, container, false)
        return binding.root
    }
}