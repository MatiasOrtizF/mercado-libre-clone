package com.mfo.mercadolibreclone.domain.usecase.favorites

import com.mfo.mercadolibreclone.domain.Repository
import javax.inject.Inject

class GetProductInFavoritesUseCase @Inject constructor(private val repository: Repository){
    suspend operator fun invoke(authorization: String, productId: Long): Boolean = repository.getProductInFavorite(authorization, productId)
}