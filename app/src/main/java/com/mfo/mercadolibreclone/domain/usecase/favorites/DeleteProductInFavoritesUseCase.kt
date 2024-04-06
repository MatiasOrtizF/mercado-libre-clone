package com.mfo.mercadolibreclone.domain.usecase.favorites

import com.mfo.mercadolibreclone.domain.Repository
import javax.inject.Inject

class DeleteProductInFavoritesUseCase @Inject constructor(private val repository: Repository) {
    suspend operator fun invoke(authorization: String, id: Long): Boolean = repository.deleteProductInFavorite(authorization, id)
}