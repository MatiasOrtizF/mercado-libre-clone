package com.mfo.mercadolibreclone.domain.usecase.cart

import com.mfo.mercadolibreclone.domain.Repository
import javax.inject.Inject

class DeleteProductInCartUseCase @Inject constructor(private val repository: Repository) {
    suspend operator fun invoke(authorization: String, id: Long): Boolean = repository.deleteProductInCart(authorization, id)

}