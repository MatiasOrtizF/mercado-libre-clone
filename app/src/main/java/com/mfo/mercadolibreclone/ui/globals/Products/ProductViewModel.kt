package com.mfo.mercadolibreclone.ui.globals.Products

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mfo.mercadolibreclone.domain.model.Product
import com.mfo.mercadolibreclone.domain.usecase.GetAllProductsByCategory
import com.mfo.mercadolibreclone.domain.usecase.favorites.PostProductInFavoriteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(private val getProductsByCategoryUseCase: GetAllProductsByCategory, private val postProductInFavoriteUseCase: PostProductInFavoriteUseCase): ViewModel() {

    private var _product = MutableStateFlow<List<Product>>(emptyList())
    val product: StateFlow<List<Product>> = _product

    private var _state = MutableStateFlow<ProductState>(ProductState.Loading)
    val state: StateFlow<ProductState> = _state

    fun getAllProductsByCategory(categoryName: String) {
        viewModelScope.launch {
            _state.value = ProductState.Loading
            try {
                val result = withContext(Dispatchers.IO) { getProductsByCategoryUseCase(categoryName) }
                if(result != null) {
                    _product.value = result
                    _state.value = ProductState.Success(result.toMutableList())
                } else {
                    _state.value =
                        ProductState.Error("Error occurred, Please try again later.")
                }
            } catch (e: Exception) {
                val errorMessage: String = e.message.toString()
                _state.value = ProductState.Error(errorMessage)
            }
        }
    }
}