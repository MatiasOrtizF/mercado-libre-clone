package com.mfo.mercadolibreclone.ui.cart

import com.mfo.mercadolibreclone.data.network.response.CartResponse

sealed class CartState {
    data object Loading: CartState()
    data class Error(val error: String): CartState()
    data class Success(val products: MutableList<CartResponse>, val message: String? = null): CartState()
}