package com.mfo.mercadolibreclone.ui.searchDetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mfo.mercadolibreclone.domain.model.Product
import com.mfo.mercadolibreclone.domain.usecase.GetProductByProductNameUseCase
import com.mfo.mercadolibreclone.domain.usecase.favorites.PostProductInFavoriteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SearchDetailViewModel @Inject constructor(private val getProductByProductNameUseCase: GetProductByProductNameUseCase, private val postProductInFavoriteUseCase: PostProductInFavoriteUseCase): ViewModel() {
    private var _product = MutableStateFlow<List<Product>>(emptyList())
    val product: StateFlow<List<Product>> = _product

    private var _state = MutableStateFlow<SearchDetailState>(SearchDetailState.Loading)
    val state: StateFlow<SearchDetailState> = _state

    fun searchProductByName(word: String) {
        viewModelScope.launch {
            _state.value = SearchDetailState.Loading
            try {
                val result = withContext(Dispatchers.IO) { getProductByProductNameUseCase(word) }
                if(result != null) {
                    _product.value = result
                    _state.value = SearchDetailState.Success(result.toMutableList())
                } else {
                    _state.value =
                        SearchDetailState.Error("Error occurred, Please try again later.")
                }
            } catch (e: Exception) {
                val errorMessage: String = e.message.toString()
                _state.value = SearchDetailState.Error(errorMessage)
            }
        }
    }
}