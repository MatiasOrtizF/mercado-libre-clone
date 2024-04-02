package com.mfo.mercadolibreclone.domain.usecase.favorites

import com.mfo.mercadolibreclone.domain.Repository
import com.mfo.mercadolibreclone.domain.model.Car
import javax.inject.Inject

class GetProductUseCase @Inject constructor(private val repository: Repository) {
    suspend operator fun invoke(category: String, id: Long): Car? = repository.getProduct(category, id)
}