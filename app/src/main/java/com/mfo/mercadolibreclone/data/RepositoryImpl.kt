package com.mfo.mercadolibreclone.data

import android.util.Log
import com.mfo.mercadolibreclone.data.network.MeliCloneApiService
import com.mfo.mercadolibreclone.data.network.response.CartResponse
import com.mfo.mercadolibreclone.data.network.response.FavoriteResponse
import com.mfo.mercadolibreclone.data.network.response.LoginResponse
import com.mfo.mercadolibreclone.domain.Repository
import com.mfo.mercadolibreclone.domain.model.Car
import com.mfo.mercadolibreclone.domain.model.LoginRequest
import com.mfo.mercadolibreclone.domain.model.Product
import retrofit2.HttpException
import javax.inject.Inject

class RepositoryImpl @Inject constructor(private val apiService: MeliCloneApiService): Repository {

    // products
    override suspend fun getAllByCategory(categoryName: String): List<Product>? {
        runCatching {
            val products = apiService.getProductByCategory(categoryName)
            products.map {
                it.toDomain()
            }
        }
            .onSuccess { products -> return products }
            .onFailure { throwable ->
                val errorMessage = when (throwable) {
                    is HttpException -> throwable.response()?.errorBody()?.string()
                    else -> null
                } ?: "An error occurred: ${throwable.message}"
                Log.i("mfo", "Error occurred: $errorMessage")
                throw Exception(errorMessage)
            }
        return null
    }

    override suspend fun getProduct(category: String, id: Long): Car? {
        runCatching { apiService.getProduct(category, id) }
            .onSuccess { return it.toDomain() }
            .onFailure { throwable ->
                val errorMessage = when (throwable) {
                    is HttpException -> throwable.response()?.errorBody()?.string()
                    else -> null
                } ?: "An error occurred: ${throwable.message}"
                Log.i("mfo", "Error occurred: $errorMessage")
                throw Exception(errorMessage)
            }
        return null
    }

    override suspend fun searchProductByName(word: String): List<Product>? {
        runCatching {
            val products = apiService.searchProductByName(word)
            products.map {
                it.toDomain()
            }
        }
            .onSuccess { products -> return products }
            .onFailure { throwable ->
                val errorMessage = when (throwable) {
                    is HttpException -> throwable.response()?.errorBody()?.string()
                    else -> null
                } ?: "An error occurred: ${throwable.message}"
                Log.i("mfo", "Error occurred: $errorMessage")
                throw Exception(errorMessage)
            }
        return null
    }

    // users

    override suspend fun authenticationUser(loginRequest: LoginRequest): LoginResponse? {
        runCatching { apiService.authenticationUser(loginRequest) }
            .onSuccess { return it.toDomain() }
            .onFailure { throwable ->
                val errorMessage = when (throwable) {
                    is HttpException -> throwable.response()?.errorBody()?.string()
                    else -> null
                } ?: "An error occurred: ${throwable.message}"
                Log.i("mfo", "Error occurred: $errorMessage")
                throw Exception(errorMessage)
            }
        return null
    }

    //favorites
    override suspend fun getAllProductsInFavorites(authorization: String): List<FavoriteResponse>? {
        runCatching {
            val products = apiService.getFavorites(authorization)
            products.map {
                it.toDomain()
            }
        }
            .onSuccess { products -> return products }
            .onFailure { throwable ->
                val errorMessage = when (throwable) {
                    is HttpException -> throwable.response()?.errorBody()?.string()
                    else -> null
                } ?: "An error occurred: ${throwable.message}"
                Log.i("mfo", "Error occurred: $errorMessage")
                throw Exception(errorMessage)
            }
        return null
    }

    override suspend fun addProductInFavorite(authorization: String, productId: Long): FavoriteResponse? {
        runCatching { apiService.addFavorite(authorization, productId) }
            .onSuccess { it.toDomain() }
            .onFailure { throwable ->
                val errorMessage = when (throwable) {
                    is HttpException -> throwable.response()?.errorBody()?.string()
                    else -> null
                } ?: "An error occurred: ${throwable.message}"
                Log.i("mfo", "Error occurred: $errorMessage")
                throw Exception(errorMessage)
            }
        return null
    }

    override suspend fun deleteProductInFavorite(authorization: String, id: Long): Boolean {
        return runCatching {
            apiService.deleteFavorite(authorization, id)
        }.fold(
            onSuccess = {
                true
            },
            onFailure = { throwable ->
                val errorMessage = when (throwable) {
                    is HttpException -> throwable.response()?.errorBody()?.string()
                    else -> null
                } ?: "An error occurred: ${throwable.message}"
                Log.i("mfo", "Error occurred: $errorMessage")
                throw Exception(errorMessage)
            }
        )
    }

    //cart
    override suspend fun getAllProductsInCart(authorization: String): List<CartResponse>? {
        runCatching {
            val products = apiService.getCart(authorization)
            products.map {
                it.toDomain()
            }
        }
            .onSuccess { products -> return products }
            .onFailure { throwable ->
                val errorMessage = when (throwable) {
                    is HttpException -> throwable.response()?.errorBody()?.string()
                    else -> null
                } ?: "An error occurred: ${throwable.message}"
                Log.i("mfo", "Error occurred: $errorMessage")
                throw Exception(errorMessage)
            }
        return null
    }

}