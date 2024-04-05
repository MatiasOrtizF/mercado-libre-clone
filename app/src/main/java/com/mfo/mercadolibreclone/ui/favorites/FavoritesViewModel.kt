package com.mfo.mercadolibreclone.ui.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mfo.mercadolibreclone.data.network.response.FavoriteResponse
import com.mfo.mercadolibreclone.domain.usecase.favorites.GetAllProductsInFavoritesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(private val getAllProductsInFavoritesUseCase: GetAllProductsInFavoritesUseCase): ViewModel() {
    private var _product = MutableStateFlow<List<FavoriteResponse>>(emptyList())
    val product: StateFlow<List<FavoriteResponse>> = _product

    private var _state = MutableStateFlow<FavoriteState>(FavoriteState.Loading)
    val state: StateFlow<FavoriteState> = _state

    fun getAllProductsInFavorites(authorization: String) {
        viewModelScope.launch {
            _state.value = FavoriteState.Loading
            try {
                val result = withContext(Dispatchers.IO) { getAllProductsInFavoritesUseCase(authorization) }
                if(result != null) {
                    _product.value = result
                    _state.value = FavoriteState.Success(result.toMutableList())
                } else {
                    _state.value =
                        FavoriteState.Error("Error occurred, Please try again later.")
                }
            } catch (e: Exception) {
                val errorMessage: String = e.message.toString()
                _state.value = FavoriteState.Error(errorMessage)
            }
        }
    }

}