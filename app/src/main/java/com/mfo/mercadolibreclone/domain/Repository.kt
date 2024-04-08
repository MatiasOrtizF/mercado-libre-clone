package com.mfo.mercadolibreclone.domain

import com.mfo.mercadolibreclone.data.network.response.CartResponse
import com.mfo.mercadolibreclone.data.network.response.FavoriteResponse
import com.mfo.mercadolibreclone.data.network.response.LoginResponse
import com.mfo.mercadolibreclone.domain.model.Car
import com.mfo.mercadolibreclone.domain.model.LoginRequest
import com.mfo.mercadolibreclone.domain.model.Product

interface Repository {
    //products
    suspend fun getProduct(category: String, id: Long): Car?
    suspend fun getAllByCategory(category: String): List<Product>?

    //login
    suspend fun authenticationUser(loginRequest: LoginRequest): LoginResponse?

    //favorites
    suspend fun getAllProductsInFavorites(authorization: String): List<FavoriteResponse>?
    suspend fun addProductInFavorite(authorization: String, productId: Long): FavoriteResponse?
    suspend fun deleteProductInFavorite(authorization: String, id: Long): Boolean

    //cart
    suspend fun getAllProductsInCart(authorization: String): List<CartResponse>?
}