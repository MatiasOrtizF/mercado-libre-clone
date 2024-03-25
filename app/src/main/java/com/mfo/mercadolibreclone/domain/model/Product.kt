package com.mfo.mercadolibreclone.domain.model

data class Product(
    val id: Long,
    val category: String,
    val title: String,
    val description: String,
    val price: Double,
    val discountPercentage: Double?,
    val rating: Double?,
    val stock: Double?,
    val image: String
) {

    fun toDomain(): Product {
        return Product(id, category, title, description, price, discountPercentage, rating, stock, image )
    }
}