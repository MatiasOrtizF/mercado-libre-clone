package com.mfo.mercadolibreclone.domain

import com.mfo.mercadolibreclone.data.network.response.LoginResponse
import com.mfo.mercadolibreclone.domain.model.LoginRequest
import com.mfo.mercadolibreclone.domain.model.Product

interface Repository {
    suspend fun getAllByCategory(category: String): List<Product>?
    suspend fun authenticationUser(loginRequest: LoginRequest): LoginResponse?
}