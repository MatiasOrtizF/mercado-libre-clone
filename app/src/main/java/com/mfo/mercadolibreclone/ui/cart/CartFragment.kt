package com.mfo.mercadolibreclone.ui.cart

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.mfo.mercadolibreclone.databinding.FragmentCartBinding
import com.mfo.mercadolibreclone.ui.cart.adapter.CartAdapter
import com.mfo.mercadolibreclone.utils.PreferenceHelper
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CartFragment : Fragment() {

    private lateinit var cartAdapter: CartAdapter
    private val cartViewModel: CartViewModel by viewModels()

    private var _binding: FragmentCartBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val preferences = PreferenceHelper.defaultPrefs(requireContext())
        val token: String = preferences.getString("jwt", "").toString()
        cartViewModel.getAllProductsInCart(token)
        initUI()
    }

    private fun initUI() {
        initList()
        initUIState()
        initListeners()
    }

    private fun initListeners() {
        binding.btnBack.setOnClickListener {
            findNavController().navigate(CartFragmentDirections.actionIdCartFragmentToIdHomeFragment())
        }
        binding.btnBuy.setOnClickListener {
            println("continuar compra")
        }
    }

    private fun initUIState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                cartViewModel.state.collect {
                    when(it) {
                        CartState.Loading -> loadingState()
                        is CartState.Error -> errorState(it.error)
                        is CartState.Success -> successSate(it)
                    }
                }
            }
        }
    }

    private fun initList() {
        cartAdapter = CartAdapter(
            onItemSelected = {
                findNavController().navigate(CartFragmentDirections.actionIdCartFragmentToIdProductDetailFragment(it.product.id, it.product.subCategory))
            },
            onCartDeleteButtonClicked = { id, position ->
                deleteToCart(id, position)
            },
            onFavoriteButtonClicked = { productId ->
                addToFavorite(productId)
            }
        )
        binding.rvCart.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = cartAdapter
        }
    }

    private fun deleteToCart(id: Long, position: Int) {
        val preferences = PreferenceHelper.defaultPrefs(requireContext())
        val token: String = preferences.getString("jwt", "").toString()
        lifecycleScope.launch {
            val isDelete = cartViewModel.deleteProductInCart(token, id)
            if (isDelete) {
                cartAdapter.onDeleteItem(position)

                if (cartAdapter.itemCount == 0) {
                    binding.layoutCartEmpty.isVisible = true
                    binding.constraintLayoutBottom.isVisible = false
                } else {
                    updateDataBuy()
                }

                binding.pb.isVisible = false
            }
        }
    }

    private fun addToFavorite(productId: Long) {
        Toast.makeText(requireContext(), "¡listo! guardaste el producto en favoritos", Toast.LENGTH_SHORT).show()
        val preferences = PreferenceHelper.defaultPrefs(requireContext())
        val token: String = preferences.getString("jwt", "").toString()
        cartViewModel.addProductInFavoriteUseCase(token, productId)
    }

    private fun loadingState() {
        binding.pb.isVisible = true
    }

    private fun errorState(error: String) {
        binding.pb.isVisible = false
        val context = binding.root.context
        Toast.makeText(context, "Error: $error", Toast.LENGTH_SHORT).show()
        if(error == "Unauthorized: invalid token") {
            binding.constraintLayoutBottom.isVisible = false
            binding.layoutCartEmpty.isVisible = true
        }
    }

    private fun successSate(state: CartState.Success) {
        binding.pb.isVisible = false
        if(state.products.isEmpty()) {
            binding.layoutCartEmpty.isVisible = true
        } else {
            binding.layoutCartEmpty.isVisible = false
            cartAdapter.updateList(state.products)

            updateDataBuy()
        }
    }

    private fun updateDataBuy() {
        binding.tvTotalProducts.text = "Products (${cartAdapter.itemCount})"

        val (totalPrice, totalShipping) = cartAdapter.calculateTotalPriceShipping()
        binding.tvTotalPrice.text = "$ $totalPrice"
        binding.tvTotalShipment.text = "$ $totalShipping"
        binding.tvTotalPriceShipping.text = "$ ${totalPrice + totalShipping}"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCartBinding.inflate(inflater, container, false)
        return binding.root
    }
}