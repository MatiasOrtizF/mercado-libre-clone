package com.mfo.mercadolibreclone.ui.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mfo.mercadolibreclone.data.network.response.CartResponse
import com.mfo.mercadolibreclone.domain.usecase.cart.DeleteProductInCartUseCase
import com.mfo.mercadolibreclone.domain.usecase.cart.GetAllProductsInCartUseCase
import com.mfo.mercadolibreclone.domain.usecase.favorites.PostProductInFavoriteUseCase
import com.mfo.mercadolibreclone.ui.cart.adapter.CartAdapter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(private val getAllProductsInCartUseCase: GetAllProductsInCartUseCase, private val deleteProductInCartUseCase: DeleteProductInCartUseCase, private val postProductInFavoriteUseCase: PostProductInFavoriteUseCase): ViewModel() {
    private var _product = MutableStateFlow<List<CartResponse>>(emptyList())
    val product: StateFlow<List<CartResponse>> = _product

    private var _state = MutableStateFlow<CartState>(CartState.Loading)
    val state: StateFlow<CartState> = _state

    private lateinit var cartAdapter: CartAdapter

    fun getAllProductsInCart(authorization: String) {
        viewModelScope.launch {
            _state.value = CartState.Loading
            try {
                val result = withContext(Dispatchers.IO) { getAllProductsInCartUseCase(authorization) }
                if(result != null) {
                    _product.value = result
                    _state.value = CartState.Success(result.toMutableList())
                } else {
                    _state.value = CartState.Error("Error occurred, Please try again later.")
                }
            } catch (e: Exception) {
                val errorMessage: String = e.message.toString()
                _state.value = CartState.Error(errorMessage)
            }
        }
    }

    suspend fun deleteProductInCart(authorization: String, id: Long): Boolean {
        return try {
            _state.value = CartState.Loading
            withContext(Dispatchers.IO) {
                deleteProductInCartUseCase(authorization, id)
                true
            }
        } catch (e: Exception) {
            val errorMessage: String = e.message.toString()
            _state.value = CartState.Error(errorMessage)
            false
        }
    }

    fun addProductInFavoriteUseCase(authorization: String, productId: Long) {
        viewModelScope.launch {
            try {
                withContext(Dispatchers.IO) { postProductInFavoriteUseCase(authorization, productId) }
            } catch (e: Exception) {
                val errorMessage: String = e.message.toString()
                _state.value = CartState.Error(errorMessage)
            }
        }
    }
}