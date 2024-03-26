package com.mfo.mercadolibreclone.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.mfo.mercadolibreclone.R
import com.mfo.mercadolibreclone.databinding.ActivityLoginBinding
import com.mfo.mercadolibreclone.domain.model.LoginRequest
import com.mfo.mercadolibreclone.ui.MainActivity
import com.mfo.mercadolibreclone.ui.category.CategoryFragmentDirections
import com.mfo.mercadolibreclone.utils.PreferenceHelper
import com.mfo.mercadolibreclone.utils.PreferenceHelper.set
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val loginViewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initUI()
    }

    private fun initUI() {
        initUIState()
        initListeners()
    }

    private fun initListeners() {
        binding.btnLoginn.setOnClickListener {
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()
            val loginRequest = LoginRequest(email, password)

            loginViewModel.authenticationUser(loginRequest)
            loadingState()
        }
    }

    private fun initUIState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                loginViewModel.state.collect{
                    when(it) {
                        is LoginState.Error -> errorState(it.error)
                        LoginState.Loading -> {}
                        is LoginState.Success -> successSate(it)
                    }
                }
            }
        }
    }

    private fun loadingState() {
        binding.pb.isVisible = true
    }

    private fun errorState(error: String) {
        binding.pb.isVisible = false
        val context = binding.root.context
        Toast.makeText(context, "Error: $error", Toast.LENGTH_SHORT).show()
    }

    private fun successSate(state: LoginState.Success) {
        binding.pb.isVisible = false
        val jwt: String = state.token
        createSessionPreference(jwt)
        goToHome()
    }

    private fun createSessionPreference(jwt: String) {
        val preferences = PreferenceHelper.defaultPrefs(this)
        preferences["jwt"] = jwt
    }

    private fun goToHome() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}