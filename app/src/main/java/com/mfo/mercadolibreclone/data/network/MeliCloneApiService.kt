package com.mfo.mercadolibreclone.data.network

import com.mfo.mercadolibreclone.data.network.response.FavoriteResponse
import com.mfo.mercadolibreclone.data.network.response.LoginResponse
import com.mfo.mercadolibreclone.domain.model.Car
import com.mfo.mercadolibreclone.domain.model.LoginRequest
import com.mfo.mercadolibreclone.domain.model.Product
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface MeliCloneApiService {

    // products
    @GET("product/category/{categoryName}")
    suspend fun getProductByCategory(@Path ("categoryName") categoryName: String): List<Product>

    @GET("product/{category}/{id}")
    suspend fun getProduct(
        @Path ("category") category: String,
        @Path ("id") id: Long
    ): Car

    @POST("login")
    suspend fun authenticationUser(@Body loginRequest: LoginRequest): LoginResponse

    // favorites
    @POST("favorite/{id}")
    suspend fun addFavorite(
        @Header ("Authorization") authorization: String,
        @Path ("id") productId: Long
    ): FavoriteResponse

    @GET("favorite")
    suspend fun getFavorites( @Header ("Authorization") authorization: String ): List<FavoriteResponse>

    @DELETE("favorite/{id}")
    suspend fun deleteFavorite(
        @Header ("Authorization") authorization: String,
        @Path ("id") favoriteProductId: Long
    ): Boolean // change this
}