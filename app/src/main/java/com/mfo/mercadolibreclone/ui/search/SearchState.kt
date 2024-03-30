package com.mfo.mercadolibreclone.ui.search

sealed class SearchState {
    data object Loading: SearchState()
    data class Error(val error: String): SearchState()
    data class Success(val products: MutableList<String>): SearchState()
}