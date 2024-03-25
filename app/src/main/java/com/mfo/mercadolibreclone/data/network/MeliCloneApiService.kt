package com.mfo.mercadolibreclone.data.network

import com.mfo.mercadolibreclone.domain.model.Product
import retrofit2.http.GET
import retrofit2.http.Path

interface MeliCloneApiService {

    @GET("product/category/{categoryName}")
    suspend fun getProductByCategory(@Path ("categoryName") categoryName: String): List<Product>
}