package com.mfo.mercadolibreclone.ui.globals

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.mfo.mercadolibreclone.R
import com.mfo.mercadolibreclone.databinding.FragmentProductBinding
import com.mfo.mercadolibreclone.ui.category.CategoryFragmentDirections
import com.mfo.mercadolibreclone.ui.category.adapter.CategoryAdapter
import com.mfo.mercadolibreclone.ui.globals.adapter.ProductAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ProductFragment : Fragment() {

    val args: ProductFragmentArgs by navArgs()
    private lateinit var productAdapter: ProductAdapter
    private val productViewModel: ProductViewModel by viewModels()

    private var _binding: FragmentProductBinding? = null
    private val binding get() = _binding!!


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val categoryName: String = args.categoryName
        productViewModel.getAllProductsByCategory(categoryName.lowercase())
        initUI()
    }

    private fun initUI() {
        initList()
        initUIState()
        initListeners()
    }

    private fun initListeners() {

    }

    private fun initUIState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                productViewModel.product.collect {
                    productAdapter.updateList(it)
                }
            }
        }
    }

    private fun initList() {
        productAdapter = ProductAdapter(onItemSelected = {
            println("Item selected")
            //findNavController().navigate(CategoryFragmentDirections.actionIdCategoryFragmentToIdProductFragment(getString(it.name)))
        })

        binding.rvProduct.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = productAdapter
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProductBinding.inflate(layoutInflater, container, false)
        return binding.root
    }
}