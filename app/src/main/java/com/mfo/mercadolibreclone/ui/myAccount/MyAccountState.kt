package com.mfo.mercadolibreclone.ui.myAccount

import com.mfo.mercadolibreclone.data.network.response.UserResponse

sealed class MyAccountState {
    data object Loading: MyAccountState()
    data class Error(val error: String): MyAccountState()
    data class Success(val user: UserResponse): MyAccountState()
}