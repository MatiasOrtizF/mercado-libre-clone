package com.mfo.mercadolibreclone.ui.category

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mfo.mercadolibreclone.databinding.ActivityCategoriesBinding
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.mfo.mercadolibreclone.ui.category.adapter.CategoryAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CategoriesActivity: AppCompatActivity() {

    private val categoryViewModel: CategoryViewModel by viewModels<CategoryViewModel>()
    private lateinit var categoryAdapter: CategoryAdapter

    private lateinit var binding: ActivityCategoriesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoriesBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun initUI() {
        initList()
        initUIState()
    }

    private fun initList() {
        categoryAdapter = CategoryAdapter()

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
}