package com.mfo.mercadolibreclone.ui.globals.ProductDetail

import com.mfo.mercadolibreclone.domain.model.Car

sealed class ProductDetailState {
    data object Loading: ProductDetailState()
    data class Error(val error: String): ProductDetailState()
    data class Success(val car: Car): ProductDetailState()
}