package com.mfo.mercadolibreclone.ui

import com.mfo.mercadolibreclone.data.network.response.UserResponse

sealed class MainState {
    data object Loading: MainState()
    data class Error(val error: String): MainState()
    data class Success(val user: UserResponse): MainState()
}