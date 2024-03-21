package com.mfo.mercadolibreclone.ui.category

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import com.mfo.mercadolibreclone.R
import com.mfo.mercadolibreclone.databinding.FragmentCategoryBinding
import com.mfo.mercadolibreclone.ui.MainActivity
import com.mfo.mercadolibreclone.ui.category.adapter.CategoryAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CategoryFragment: Fragment() {

    private val categoryViewModel by viewModels<CategoryViewModel>()
    private lateinit var categoryAdapter: CategoryAdapter

    private var _binding: FragmentCategoryBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
    }

    private fun initUI() {
        initList()
        initUIState()
        initListeners()
    }

    private fun initList() {
        categoryAdapter = CategoryAdapter(onItemSelected = {
            Toast.makeText(context, getString(it.name) ,Toast.LENGTH_LONG).show()
            println(getString(it.name))
        })

        binding.rvCategory.apply {
            layoutManager = GridLayoutManager(context, 3)
            adapter = categoryAdapter
        }
    }

    private fun initUIState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                categoryViewModel.category.collect {
                    categoryAdapter.updateList(it)
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCategoryBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    private fun initListeners() {
        /*binding.btnBack.setOnClickListener {
            goToHome()
        }*/
    }

    private fun goToHome() {
        println("go to home")
    }

}