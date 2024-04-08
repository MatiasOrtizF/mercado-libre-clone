package com.mfo.mercadolibreclone.domain.usecase.cart

import com.mfo.mercadolibreclone.data.network.response.CartResponse
import com.mfo.mercadolibreclone.domain.Repository
import javax.inject.Inject

class GetAllProductsInCartUseCase @Inject constructor(private val repository: Repository) {
    suspend operator fun invoke(authorization: String): List<CartResponse>? = repository.getAllProductsInCart(authorization)
}