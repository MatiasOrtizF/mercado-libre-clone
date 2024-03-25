package com.mfo.mercadolibreclone.domain.usecase

import com.mfo.mercadolibreclone.domain.Repository
import com.mfo.mercadolibreclone.domain.model.Product
import javax.inject.Inject

class getAllProductsByCategoryUseCase @Inject constructor(private val repository: Repository) {
    suspend operator fun invoke(categoryName: String): List<Product>? = repository.getAllByCategory(categoryName)
}