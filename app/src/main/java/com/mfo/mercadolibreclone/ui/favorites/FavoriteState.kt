package com.mfo.mercadolibreclone.ui.favorites

import com.mfo.mercadolibreclone.data.network.response.FavoriteResponse

sealed class FavoriteState {
    data object Loading: FavoriteState()
    data class Error(val error: String): FavoriteState()
    data class Success(val products: MutableList<FavoriteResponse>): FavoriteState()
}