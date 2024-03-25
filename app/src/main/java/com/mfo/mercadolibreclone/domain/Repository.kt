package com.mfo.mercadolibreclone.domain

import com.mfo.mercadolibreclone.domain.model.Product

interface Repository {
    suspend fun getAllByCategory(category: String): List<Product>?
}