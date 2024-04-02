package com.mfo.mercadolibreclone.ui.globals.Products

import com.mfo.mercadolibreclone.domain.model.Product

sealed class ProductState {
    data object Loading: ProductState()
    data class Error(val error: String): ProductState()
    data class Success(val products: MutableList<Product>): ProductState()
}