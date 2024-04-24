package com.mfo.mercadolibreclone.ui.globals.ProductDetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mfo.mercadolibreclone.domain.usecase.favorites.GetProductInFavoritesUseCase
import com.mfo.mercadolibreclone.domain.usecase.favorites.GetProductUseCase
import com.mfo.mercadolibreclone.ui.favorites.FavoriteState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ProductDetailViewModel @Inject constructor(private val getProductUseCase: GetProductUseCase, private val getProductInFavoritesUseCase: GetProductInFavoritesUseCase): ViewModel() {

    private var _state = MutableStateFlow<ProductDetailState>(ProductDetailState.Loading)
    val state: StateFlow<ProductDetailState> = _state

    fun getProduct(category: String, id: Long) {
        viewModelScope.launch {
            _state.value = ProductDetailState.Loading
            val result = withContext(Dispatchers.IO) { getProductUseCase(category, id) }
            if(result != null) {
                print("datos del producto: $result")
                _state.value = ProductDetailState.Success(result)
            } else {
                _state.value = ProductDetailState.Error("Error occurred, Please try again later.")
            }
        }
    }

    suspend fun getProductInFavorite(authorization: String, productId: Long): Boolean {
        return try {
            withContext(Dispatchers.IO) {
                getProductInFavoritesUseCase(authorization, productId)
            }
        } catch (e: Exception) {
            val errorMessage: String = e.message.toString()
            _state.value = ProductDetailState.Error(errorMessage)
            false
        }
    }
}