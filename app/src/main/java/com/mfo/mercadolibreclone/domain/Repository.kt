package com.mfo.mercadolibreclone.domain

import com.mfo.mercadolibreclone.data.network.response.FavoriteResponse
import com.mfo.mercadolibreclone.data.network.response.LoginResponse
import com.mfo.mercadolibreclone.domain.model.Car
import com.mfo.mercadolibreclone.domain.model.LoginRequest
import com.mfo.mercadolibreclone.domain.model.Product

interface Repository {
    suspend fun getAllByCategory(category: String): List<Product>?
    suspend fun authenticationUser(loginRequest: LoginRequest): LoginResponse?
    suspend fun getAllProductsInFavorites(authorization: String): List<FavoriteResponse>?
    suspend fun addProductInFavorite(authorization: String, productId: Long): FavoriteResponse?
    suspend fun getProduct(category: String, id: Long): Car?
}