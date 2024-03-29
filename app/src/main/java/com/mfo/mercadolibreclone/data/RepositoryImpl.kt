package com.mfo.mercadolibreclone.data

import android.util.Log
import com.mfo.mercadolibreclone.data.network.MeliCloneApiService
import com.mfo.mercadolibreclone.data.network.response.LoginResponse
import com.mfo.mercadolibreclone.domain.Repository
import com.mfo.mercadolibreclone.domain.model.LoginRequest
import com.mfo.mercadolibreclone.domain.model.Product
import retrofit2.HttpException
import javax.inject.Inject

class RepositoryImpl @Inject constructor(private val apiService: MeliCloneApiService): Repository {

    // category
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
                } ?: "An error ocurred: ${throwable.message}"
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
                } ?: "An error ocurred: ${throwable.message}"
                Log.i("mfo", "Error occurred: $errorMessage")
                throw Exception(errorMessage)
            }
        return null
    }

}