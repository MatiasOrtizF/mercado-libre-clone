package com.mfo.mercadolibreclone.domain

import com.mfo.mercadolibreclone.data.network.response.CartResponse
import com.mfo.mercadolibreclone.data.network.response.FavoriteResponse
import com.mfo.mercadolibreclone.data.network.response.LoginResponse
import com.mfo.mercadolibreclone.data.network.response.UserResponse
import com.mfo.mercadolibreclone.domain.model.Car
import com.mfo.mercadolibreclone.domain.model.LoginRequest
import com.mfo.mercadolibreclone.domain.model.Product
import com.mfo.mercadolibreclone.domain.model.User

interface Repository {
    //products
    suspend fun getProduct(category: String, id: Long): Car?
    suspend fun getAllByCategory(category: String): List<Product>?
    suspend fun searchProductByName(word: String): List<Product>?

    //login
    suspend fun authenticationUser(loginRequest: LoginRequest): LoginResponse?

    //favorites
    suspend fun getAllProductsInFavorites(authorization: String): List<FavoriteResponse>?
    suspend fun addProductInFavorite(authorization: String, productId: Long): FavoriteResponse?
    suspend fun deleteProductInFavorite(authorization: String, id: Long): Boolean

    //cart
    suspend fun getAllProductsInCart(authorization: String): List<CartResponse>?

    //user
    suspend fun getUser(token: String): UserResponse?
}