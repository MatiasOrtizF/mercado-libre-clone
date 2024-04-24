package com.mfo.mercadolibreclone.ui.globals.ProductDetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.mfo.mercadolibreclone.R
import com.mfo.mercadolibreclone.databinding.FragmentProductDetailBinding
import com.mfo.mercadolibreclone.utils.PreferenceHelper
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ProductDetailFragment : Fragment() {

    val args: ProductDetailFragmentArgs by navArgs()

    private var _binding: FragmentProductDetailBinding? = null
    private val binding get() = _binding!!

    private val productDetailViewModel: ProductDetailViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val productId: Long = args.productId
        val categoryName: String = args.subCategoryName
        initUI()
        productDetailViewModel.getProduct(categoryName, productId)
        val preferences = PreferenceHelper.defaultPrefs(requireContext())
        val token: String = preferences.getString("jwt", "").toString()
        lifecycleScope.launch {
            val isDelete = productDetailViewModel.getProductInFavorite(token, productId)
            if (isDelete) {
                binding.btnAddFavorite.setImageResource(R.drawable.ic_favorited)
            }
        }
    }

    private fun initUI() {
        initUIState()
        initListeners()
    }

    private fun initUIState() {
        lifecycleScope.launch {
            productDetailViewModel.state.collect {
                when(it) {
                    ProductDetailState.Loading -> loadingState()
                    is ProductDetailState.Error -> errorState()
                    is ProductDetailState.Success -> successState(it)
                }
            }
        }
    }

    private fun loadingState() {
        //binding.pb.isVisible = true
    }

    private fun errorState() {
        //binding.pb.isVisible = false
    }

    private fun successState(state: ProductDetailState.Success) {
        //binding.pb.isVisible = false
        binding.tvTitle.text = state.car.age.toString() + " | " + state.car.kilometres + " km · Publicado hace 3 meses"
        binding.tvSubTitle.text = state.car.product.title
        Glide.with(requireContext()).load(state.car.product.image).into(binding.ivProduct)
        binding.tvPrice.text = getString(R.string.tv_currency_price, state.car.product.price)
        binding.tvDoors.text = " " + state.car.doors.toString()
        binding.tvMotor.text = " 1.4"
        binding.tvFuelType.text = " " + state.car.fuelType
        binding.tvTransmission.text = " " + state.car.transmission
        binding.tvDescription.text = state.car.product.description
        binding.tvUserName.text = getString(R.string.tv_username, state.car.user.name , state.car.user.lastName)
        binding.tvUserEmail.text = state.car.user.email
    }

    private fun initListeners() {

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProductDetailBinding.inflate(layoutInflater, container, false)
        return binding.root
    }
}