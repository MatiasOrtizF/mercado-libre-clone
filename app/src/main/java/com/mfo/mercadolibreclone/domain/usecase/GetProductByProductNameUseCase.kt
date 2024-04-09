package com.mfo.mercadolibreclone.domain.usecase

import com.mfo.mercadolibreclone.domain.Repository
import com.mfo.mercadolibreclone.domain.model.Product
import javax.inject.Inject

class GetProductByProductNameUseCase @Inject constructor(private val repository: Repository) {
    suspend operator fun invoke(word: String): List<Product>? = repository.searchProductByName(word)
}