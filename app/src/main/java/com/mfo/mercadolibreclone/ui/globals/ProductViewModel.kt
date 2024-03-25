package com.mfo.mercadolibreclone.ui.globals

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mfo.mercadolibreclone.data.providers.CategoryProvider
import com.mfo.mercadolibreclone.domain.model.CategoryInfo
import com.mfo.mercadolibreclone.domain.model.Product
import com.mfo.mercadolibreclone.domain.usecase.getAllProductsByCategoryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(private val getProductsByCategoryUseCase: getAllProductsByCategoryUseCase): ViewModel() {

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
                    _state.value = ProductState.Error("ocurrio un error, por favor intente mas tarde")
                }
            } catch (e: Exception) {
                val errorMessage: String = e.message.toString()
                _state.value = ProductState.Error(errorMessage)
            }
        }
    }
}