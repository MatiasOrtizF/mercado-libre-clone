package com.mfo.mercadolibreclone.domain.model

data class Product(
    val id: Long,
    val category: String,
    val subCategory: String,
    val title: String,
    val description: String,
    val price: Double,
    val discountPercentage: Double?,
    val rating: Double?,
    val stock: Double?,
    val image: String,
    val shipment: Double?,
    val user: User
) {

    fun toDomain(): Product {
        return Product(id, category, subCategory, title, description, price, discountPercentage, rating, stock, image, shipment, user)
    }
}