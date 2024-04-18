package com.mfo.mercadolibreclone.data.network

import com.mfo.mercadolibreclone.data.network.response.CartResponse
import com.mfo.mercadolibreclone.data.network.response.FavoriteResponse
import com.mfo.mercadolibreclone.data.network.response.LoginResponse
import com.mfo.mercadolibreclone.data.network.response.UserResponse
import com.mfo.mercadolibreclone.domain.model.Car
import com.mfo.mercadolibreclone.domain.model.LoginRequest
import com.mfo.mercadolibreclone.domain.model.Product
import com.mfo.mercadolibreclone.domain.model.User
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface MeliCloneApiService {

    // products
    @GET("product/category/{categoryName}")
    suspend fun getProductByCategory(@Path ("categoryName") categoryName: String): List<Product>

    @GET("product/{category}/{id}")
    suspend fun getProduct(
        @Path ("category") category: String,
        @Path ("id") id: Long
    ): Car

    @GET("product/search")
    suspend fun searchProductByName(@Query ("word") word: String): List<Product>

    // login
    @POST("login")
    suspend fun authenticationUser(@Body loginRequest: LoginRequest): LoginResponse

    // favorites
    @POST("favorite/{id}")
    suspend fun addFavorite(
        @Header ("Authorization") authorization: String,
        @Path ("id") productId: Long
    ): FavoriteResponse

    @GET("favorite")
    suspend fun getFavorites(@Header ("Authorization") authorization: String): List<FavoriteResponse>

    @DELETE("favorite/{id}")
    suspend fun deleteFavorite(
        @Header ("Authorization") authorization: String,
        @Path ("id") favoriteProductId: Long
    ): Boolean // change this

    //cart
    @GET("cart")
    suspend fun getCart(@Header ("Authorization") authorization: String): List<CartResponse>

    @DELETE("cart/{id}")
    suspend fun deleteCart(
        @Header ("Authorization") authorization: String,
        @Path ("id") id: Long
    ): Boolean

    //user
    @GET("user")
    suspend fun getUser(@Query ("token") token: String): UserResponse
}