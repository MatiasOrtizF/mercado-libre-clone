package com.mfo.mercadolibreclone.domain.usecase.favorites

import com.mfo.mercadolibreclone.data.network.response.FavoriteResponse
import com.mfo.mercadolibreclone.domain.Repository
import javax.inject.Inject

class GetAllProductsInFavoritesUseCase @Inject constructor(private val repository: Repository) {
    suspend operator fun invoke(authorization: String): List<FavoriteResponse>? = repository.getAllProductsInFavorites(authorization)
}