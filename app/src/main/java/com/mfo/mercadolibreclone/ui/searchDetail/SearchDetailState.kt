package com.mfo.mercadolibreclone.ui.searchDetail

import com.mfo.mercadolibreclone.domain.model.Product

sealed class SearchDetailState {
    data object Loading: SearchDetailState()
    data class Error(val error: String): SearchDetailState()
    data class Success(val products: MutableList<Product>): SearchDetailState()
}